package com.flc.ticketapp.service.model.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flc.ticketapp.service.model.response.role.RoleJoinedProjection;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

public interface UserAuthProjection {

    UUID getId();

    String getUsername();

    String getPhoneNumber();

    String getFirstName();

    String getLastName();

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate getBirthDate();

    Collection<RoleJoinedProjection> getRoles();

}
