package com.flc.ticketapp.service.model.request.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleCreateRequestModel {

    private UUID routeId;

    private Integer capacity;

    private LocalDateTime dateTime;

    private Double price;

}
