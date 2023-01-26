package com.flc.ticketapp.api.security;

import com.flc.ticketapp.service.abstracts.UserService;
import com.flc.ticketapp.service.model.response.user.UserAuthProjection;
import com.flc.ticketapp.service.objectmapping.manual.concretes.security.GrantedAuthorityMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenHelper jwtTokenHelper;
    private final UserService userService;
    private final GrantedAuthorityMapper grantedAuthorityMapper;
    private static final RequestMatcher REQUEST_MATCHER;

    static {
        String[] antPatterns = SecurityConfig.getPublicEndPoints();
        List<RequestMatcher> matchers = new ArrayList<>(antPatterns.length);
        for (String pattern : antPatterns)
            matchers.add(new AntPathRequestMatcher(pattern, null));
        REQUEST_MATCHER = new OrRequestMatcher(matchers);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return REQUEST_MATCHER.matches(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(JwtTokenHelper.SCHEME.length() + 1);
        Claims claims = jwtTokenHelper.getClaims(token);
        jwtTokenHelper.valid(claims);
        String username = claims.getSubject();

        UserAuthProjection user = userService.getUserAuthProjection(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, grantedAuthorityMapper.map(user.getRoles()));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

}