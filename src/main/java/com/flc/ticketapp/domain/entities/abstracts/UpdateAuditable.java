package com.flc.ticketapp.domain.entities.abstracts;

import java.time.LocalDateTime;

public interface UpdateAuditable extends Entity {

    LocalDateTime getModifiedAt();

    void setModifiedAt(LocalDateTime modifiedAt);

    String getModifier();

    void setModifier(String modifier);

}
