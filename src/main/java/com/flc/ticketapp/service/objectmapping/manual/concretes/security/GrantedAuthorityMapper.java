package com.flc.ticketapp.service.objectmapping.manual.concretes.security;

import com.flc.ticketapp.domain.helper.ReflectionHelper;
import com.flc.ticketapp.service.objectmapping.abstraction.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class GrantedAuthorityMapper implements Mapper<Collection<?>, List<GrantedAuthority>> {

    private static final short APPROXIMATE_AUTHORITY_COUNT_PER_ROLE = 4;

    @Override
    public List<GrantedAuthority> map(Collection<?> source) {
        if (source == null) return new ArrayList<>();
        List<GrantedAuthority> destination = new ArrayList<>(source.size() * APPROXIMATE_AUTHORITY_COUNT_PER_ROLE);
        for (Object roleObj : source) {
            String roleName = ReflectionHelper.getFieldValueByGetter(roleObj, "getName", String.class).orElse(null);
            destination.add(new SimpleGrantedAuthority(roleName));
        }
        return destination;
    }

}
