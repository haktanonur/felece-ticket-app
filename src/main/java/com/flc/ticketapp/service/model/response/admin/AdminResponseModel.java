package com.flc.ticketapp.service.model.response.admin;

import com.flc.ticketapp.service.model.response.role.RoleResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseModel {

    private UUID id;

    private String username;

    private String password;

    private List<RoleResponseModel> roles;

}
