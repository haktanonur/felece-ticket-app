package com.flc.ticketapp.service.abstracts;

import com.flc.ticketapp.service.model.request.route.RouteCreateRequestModel;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;

import java.util.UUID;

public interface RouteService {

    ServiceResponse create(RouteCreateRequestModel model);

    ServiceResponse updateFrom(UUID id, String from);

    ServiceResponse updateTo(UUID id, String to);

    ServiceResponse delete(UUID id);

}
