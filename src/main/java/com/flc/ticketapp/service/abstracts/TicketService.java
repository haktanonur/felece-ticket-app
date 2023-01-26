package com.flc.ticketapp.service.abstracts;

import com.flc.ticketapp.service.model.request.ticket.TicketCreateRequestModel;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;

import java.util.UUID;

public interface TicketService {

    ServiceResponse create(TicketCreateRequestModel model);

    ServiceResponse postpone(UUID id);

    ServiceResponse cancel(UUID id);

}
