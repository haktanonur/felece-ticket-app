package com.flc.ticketapp.service.objectmapping.manual.concretes.admin;

import com.flc.ticketapp.domain.entities.concretes.Admin;
import com.flc.ticketapp.domain.entities.concretes.Role;
import com.flc.ticketapp.service.model.request.admin.AdminCreateRequestModel;
import com.flc.ticketapp.service.objectmapping.manual.abstracts.ManualMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminCreateMapper implements ManualMapper<AdminCreateRequestModel, Admin> {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Admin map(AdminCreateRequestModel model) {
        Admin admin = new Admin();
        admin.setUsername(model.getUsername());
        admin.setPassword(passwordEncoder.encode(model.getPassword()));
        return admin;
    }

}
