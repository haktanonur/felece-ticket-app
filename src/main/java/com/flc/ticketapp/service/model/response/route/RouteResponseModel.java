package com.flc.ticketapp.service.model.response.route;

import com.flc.ticketapp.service.model.response.vehicle.VehicleResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponseModel {

    private UUID id;

    private String from;

    private String to;

    private List<VehicleResponseModel> vehicles;

}
