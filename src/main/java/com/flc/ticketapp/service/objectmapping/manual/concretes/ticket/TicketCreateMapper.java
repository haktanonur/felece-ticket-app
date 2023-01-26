package com.flc.ticketapp.service.objectmapping.manual.concretes.ticket;

import com.flc.ticketapp.domain.entities.abstracts.Identifiable;
import com.flc.ticketapp.domain.entities.concretes.Ticket;
import com.flc.ticketapp.domain.entities.concretes.User;
import com.flc.ticketapp.domain.entities.concretes.Vehicle;
import com.flc.ticketapp.service.model.request.ticket.TicketCreateRequestModel;
import com.flc.ticketapp.service.objectmapping.manual.abstracts.ManualMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketCreateMapper implements ManualMapper<TicketCreateRequestModel, Ticket> {

    @Override
    public Ticket map(TicketCreateRequestModel model) {
        Ticket ticket = new Ticket();
        ticket.setPassenger(Identifiable.getInstance(User::new, model.getPassengerId()));
        ticket.setVehicle(Identifiable.getInstance(Vehicle::new, model.getVehicleId()));
        ticket.setSeatNo(model.getSeatNo());
        return ticket;
    }

}
