package com.flc.ticketapp.service.objectmapping.manual.concretes.user;

import com.flc.ticketapp.domain.entities.concretes.Role;
import com.flc.ticketapp.domain.entities.concretes.User;
import com.flc.ticketapp.service.model.request.user.UserCreateRequestModel;
import com.flc.ticketapp.service.objectmapping.manual.abstracts.ManualMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements ManualMapper<UserCreateRequestModel, User> {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateRequestModel model) {
        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        return user;
    }

}
