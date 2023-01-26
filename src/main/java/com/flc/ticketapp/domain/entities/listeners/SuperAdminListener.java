package com.flc.ticketapp.domain.entities.listeners;

import com.flc.ticketapp.domain.constants.RoleConst;
import com.flc.ticketapp.domain.entities.concretes.Admin;
import jakarta.persistence.PrePersist;

public class SuperAdminListener {

    @PrePersist
    public void prePersist(Admin target) {
        ListenerHelper.addToRoles(target, RoleConst.Id.ADMIN);
    }

}
