package com.flc.ticketapp.api.controllers.v1;

import com.flc.ticketapp.api.common.Origin;
import com.flc.ticketapp.api.common.ResponseBuilder;
import com.flc.ticketapp.api.security.JwtTokenHelper;
import com.flc.ticketapp.domain.constants.MsgCode;
import com.flc.ticketapp.service.abstracts.UserService;
import com.flc.ticketapp.service.model.request.security.LoginRequestModel;
import com.flc.ticketapp.service.model.response.user.UserAuthProjection;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import com.flc.ticketapp.service.response.success.ServiceSuccessDataResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated

@CrossOrigin(origins = Origin.LOCALHOST_3000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtTokenHelper jwtTokenHelper;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @SneakyThrows(BadCredentialsException.class)
    public ResponseEntity<ServiceResponse> login(@RequestBody @Valid LoginRequestModel model) {
        String password = userService.getPasswordByUsername(model.getUsername());

        if (password == null)
            throw new UsernameNotFoundException(MsgCode.SECURITY_LOGIN_WRONG_USERNAME);
        if (!passwordEncoder.matches(model.getPassword(), password))
            throw new BadCredentialsException(MsgCode.SECURITY_LOGIN_WRONG_PASSWORD);

        UserAuthProjection user = userService.getUserAuthProjection(model.getUsername());
        return ResponseBuilder.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, jwtTokenHelper.generateToken(user))
                .body(new ServiceSuccessDataResponse<>(user, MsgCode.COMMON_SUCCESS));
    }

}
