package com.flc.ticketapp.service.abstracts;

import com.flc.ticketapp.service.model.request.vehicle.VehicleCreateRequestModel;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public interface VehicleService {

    ServiceResponse create(VehicleCreateRequestModel model);

    ServiceResponse updateCapacity(UUID id, Integer capacity);

    ServiceResponse updateDateTime(UUID id, LocalDateTime dateTime);

    ServiceResponse updatePrice(UUID id, Double price);

    ServiceResponse delete(UUID id);

}
