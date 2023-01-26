package com.flc.ticketapp.domain.helper;

import com.flc.ticketapp.service.objectmapping.manual.concretes.security.AuthorityNameMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityHelper {

    private final AuthorityNameMapper authorityNameMapper;

    public boolean isLoggedInUser(String username) {
        return SecurityStaticHelper.getUsername().map(currUsername -> currUsername.equals(username)).orElse(false);
    }

    public boolean isLoggedInUser(List<String> usernames) {
        if (usernames == null || usernames.isEmpty() || new HashSet<>(usernames).size() > 1) return false;
        return isLoggedInUser(usernames.get(0));
    }

    public Optional<String> getCurrentUsername() {
        return SecurityStaticHelper.getUsername();
    }

    public List<String> getAuthorities() {
        return SecurityStaticHelper.getAuthorities();
    }

}
