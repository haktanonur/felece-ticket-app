package com.flc.ticketapp.service.objectmapping.manual.concretes.user;

import com.flc.ticketapp.domain.entities.concretes.User;
import com.flc.ticketapp.service.model.response.user.UserResponseModel;
import com.flc.ticketapp.service.objectmapping.manual.abstracts.ManualMapper;
import com.flc.ticketapp.service.objectmapping.manual.concretes.role.RoleResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserResponseMapper implements ManualMapper<User, UserResponseModel> {

    private final RoleResponseMapper roleResponseMapper;

    @Override
    public UserResponseModel map(User user) {
        UserResponseModel responseModel = new UserResponseModel();
        responseModel.setUsername(user.getUsername());
        responseModel.setPassword(user.getUsername());
        responseModel.setRoles(new ArrayList<>());
        roleResponseMapper.mapCollection(user.getRoles());
        return responseModel;
    }

}
