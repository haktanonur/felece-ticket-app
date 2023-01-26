package com.flc.ticketapp.service.objectmapping.manual.concretes.security;

import com.flc.ticketapp.domain.entities.concretes.User;
import com.flc.ticketapp.service.objectmapping.manual.abstracts.ManualMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsMapper implements ManualMapper<User, org.springframework.security.core.userdetails.User> {

    private final GrantedAuthorityMapper grantedAuthorityMapper;

    @Override
    public org.springframework.security.core.userdetails.User map(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, grantedAuthorityMapper.map(user.getRoles()));

    }

}
