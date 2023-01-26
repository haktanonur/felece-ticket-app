package com.flc.ticketapp.service.objectmapping.manual.concretes.route;

import com.flc.ticketapp.domain.entities.concretes.Route;
import com.flc.ticketapp.service.model.request.route.RouteCreateRequestModel;
import com.flc.ticketapp.service.objectmapping.manual.abstracts.ManualMapper;
import org.springframework.stereotype.Component;

@Component
public class RouteCreateMapper implements ManualMapper<RouteCreateRequestModel, Route> {

    @Override
    public Route map(RouteCreateRequestModel model) {
        Route route = new Route();
        route.setFrom(model.getFrom());
        route.setTo(model.getTo());
        return route;
    }

}
