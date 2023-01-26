package com.flc.ticketapp.domain.entities.listeners;

import com.flc.ticketapp.domain.entities.abstracts.CreateAuditable;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

public class CreateAuditableListener {

    @PrePersist
    public void prePersist(CreateAuditable target) {
        target.setCreatedAt(LocalDateTime.now());
        target.setCreator("Anonymous");
    }

}
