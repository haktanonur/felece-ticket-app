package com.flc.ticketapp.service.objectmapping.manual.concretes.vehicle;

import com.flc.ticketapp.domain.entities.abstracts.Identifiable;
import com.flc.ticketapp.domain.entities.concretes.Route;
import com.flc.ticketapp.domain.entities.concretes.Vehicle;
import com.flc.ticketapp.service.model.request.vehicle.VehicleCreateRequestModel;
import com.flc.ticketapp.service.objectmapping.manual.abstracts.ManualMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleCreateMapper implements ManualMapper<VehicleCreateRequestModel, Vehicle> {

    @Override
    public Vehicle map(VehicleCreateRequestModel model) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRoute(Identifiable.getInstance(Route::new, model.getRouteId()));
        vehicle.setCapacity(model.getCapacity());
        vehicle.setPrice(model.getPrice());
        vehicle.setDateTime(model.getDateTime());
        return vehicle;
    }

}
