package com.flc.ticketapp.service.model.request.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteCreateRequestModel {

    private String from;

    private String to;

}
