package com.flc.ticketapp.service.objectmapping.manual.concretes.security;

import com.flc.ticketapp.service.objectmapping.abstraction.CollectionMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthorityNameMapper implements CollectionMapper<GrantedAuthority, String> {

    @Override
    public String map(GrantedAuthority source) {
        return source.getAuthority();
    }

}
