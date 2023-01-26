package com.flc.ticketapp.domain.entities.listeners;

import com.flc.ticketapp.domain.entities.abstracts.UpdateAuditable;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class UpdateAuditableListener {

    @PreUpdate
    public void preUpdate(UpdateAuditable target) {
        target.setModifiedAt(LocalDateTime.now());
        target.setModifier("Anonymous");
    }

}
