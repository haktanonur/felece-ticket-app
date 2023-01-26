package com.flc.ticketapp.service.model.request.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreateRequestModel {

    private UUID passengerId;

    private UUID vehicleId;

    private Integer seatNo;

}
