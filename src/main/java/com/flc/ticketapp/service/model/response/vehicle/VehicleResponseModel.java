package com.flc.ticketapp.service.model.response.vehicle;

import com.flc.ticketapp.service.model.response.route.RouteResponseModel;
import com.flc.ticketapp.service.model.response.ticket.TicketResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseModel {

    private UUID id;

    private Integer capacity;

    private RouteResponseModel route;

    private LocalDateTime dateTime;

    private Integer price;

    private List<TicketResponseModel> tickets;

}
