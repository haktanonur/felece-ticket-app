package com.flc.ticketapp.service.objectmapping.manual.concretes.role;

import com.flc.ticketapp.domain.entities.concretes.Role;
import com.flc.ticketapp.service.model.response.role.RoleResponseModel;
import com.flc.ticketapp.service.objectmapping.manual.abstracts.ManualMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleResponseMapper implements ManualMapper<Role, RoleResponseModel> {

    @Override
    public RoleResponseModel map(Role role) {
        RoleResponseModel roleResponseModel = new RoleResponseModel();
        roleResponseModel.setId(role.getId());
        roleResponseModel.setName(role.getName());
        return roleResponseModel;
    }

}
