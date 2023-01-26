package com.flc.ticketapp.api.controllers.v1;

import com.flc.ticketapp.api.common.Origin;
import com.flc.ticketapp.api.common.ResponseBuilder;
import com.flc.ticketapp.domain.constants.RoleConst;
import com.flc.ticketapp.service.abstracts.RouteService;
import com.flc.ticketapp.service.model.request.route.RouteCreateRequestModel;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@Validated

@CrossOrigin(origins = Origin.LOCALHOST_3000)
@RestController
@RequestMapping("/api/v1/route")
public class RouteController {

    private final RouteService routeService;

    @Secured(RoleConst.Name.ADMIN)
    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> create(@RequestBody @Valid RouteCreateRequestModel model) {
        return ResponseBuilder.status(HttpStatus.CREATED).body(routeService.create(model));
    }

    @Secured(RoleConst.Name.ADMIN)
    @PutMapping("/update/from")
    public ResponseEntity<ServiceResponse> updateFrom(@RequestParam UUID id, @RequestParam @Size(min = 2, max = 127, message = "dsa") String from) {
        return ResponseBuilder.status(HttpStatus.OK)
                .body(routeService.updateFrom(id, from));
    }

    @Secured(RoleConst.Name.ADMIN)
    @PutMapping("/update/to")
    public ResponseEntity<ServiceResponse> updateTo(@RequestParam UUID id, @RequestParam @Size(min = 2, max = 127, message = "dsa") String to) {
        return ResponseBuilder.status(HttpStatus.OK).body(routeService.updateFrom(id, to));
    }

    @Secured(RoleConst.Name.ADMIN)
    @DeleteMapping("/delete")
    public ResponseEntity<ServiceResponse> delete(@RequestParam UUID id) {
        return ResponseBuilder.status(HttpStatus.OK).body(routeService.delete(id));
    }

}
