package com.flc.ticketapp.service.objectmapping.manual.concretes.admin;

import com.flc.ticketapp.domain.entities.concretes.Admin;
import com.flc.ticketapp.service.model.response.admin.AdminResponseModel;
import com.flc.ticketapp.service.objectmapping.manual.abstracts.ManualMapper;
import com.flc.ticketapp.service.objectmapping.manual.concretes.role.RoleResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class AdminResponseMapper implements ManualMapper<Admin, AdminResponseModel> {

    private final RoleResponseMapper roleResponseMapper;

    @Override
    public AdminResponseModel map(Admin admin) {
        AdminResponseModel responseModel = new AdminResponseModel();
        responseModel.setUsername(admin.getUsername());
        responseModel.setPassword(admin.getUsername());
        responseModel.setRoles(new ArrayList<>());
        roleResponseMapper.mapCollection(admin.getRoles());
        return responseModel;
    }

}
