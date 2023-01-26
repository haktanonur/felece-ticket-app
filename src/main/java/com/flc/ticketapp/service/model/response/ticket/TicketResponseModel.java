package com.flc.ticketapp.service.model.response.ticket;

import com.flc.ticketapp.domain.enumeration.TicketStatus;
import com.flc.ticketapp.service.model.response.user.UserResponseModel;
import com.flc.ticketapp.service.model.response.vehicle.VehicleResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseModel {

    private UUID id;

    private UserResponseModel passenger;

    private VehicleResponseModel vehicle;

    private Integer seatNo;

    private TicketStatus status;

}
