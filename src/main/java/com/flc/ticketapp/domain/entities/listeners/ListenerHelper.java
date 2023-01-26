package com.flc.ticketapp.domain.entities.listeners;

import com.flc.ticketapp.domain.constants.RoleConst;
import com.flc.ticketapp.domain.entities.concretes.Role;
import com.flc.ticketapp.domain.entities.concretes.User;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class ListenerHelper {

    public void addToRoles(User user, Integer roleId) {
        Role role = RoleConst.getInstanceById(roleId);
        if (user.getRoles() == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
        } else user.getRoles().add(role);
    }

}
