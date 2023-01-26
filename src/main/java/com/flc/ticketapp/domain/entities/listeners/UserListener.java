package com.flc.ticketapp.domain.entities.listeners;


import com.flc.ticketapp.domain.constants.RoleConst;
import com.flc.ticketapp.domain.entities.concretes.Role;
import com.flc.ticketapp.domain.entities.concretes.User;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;

public class UserListener {

    @PrePersist
    public void prePersist(User target) {
        ListenerHelper.addToRoles(target, RoleConst.Id.USER);
    }

    @PreRemove
    public void preRemove(User target) {
        for (Role role : target.getRoles())
            target.getRoles().remove(role);
    }

}
