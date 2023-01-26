package com.flc.ticketapp.api.controllers.v1;

import com.flc.ticketapp.api.common.Origin;
import com.flc.ticketapp.api.common.ResponseBuilder;
import com.flc.ticketapp.domain.constants.RoleConst;
import com.flc.ticketapp.service.abstracts.TicketService;
import com.flc.ticketapp.service.model.request.ticket.TicketCreateRequestModel;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@Validated

@CrossOrigin(origins = Origin.LOCALHOST_3000)
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Secured(RoleConst.Name.USER)
    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> create(@RequestBody @Valid TicketCreateRequestModel model) {
        return ResponseBuilder.status(HttpStatus.CREATED).body(ticketService.create(model));
    }

    @PreAuthorize("@securityHelper.isLoggedInUser(@ticketRepository.findUsernameById(#id).orElseThrow())")
    @PutMapping("/postpone")
    public ResponseEntity<ServiceResponse> postpone(@RequestParam UUID id) {
        return ResponseBuilder.status(HttpStatus.OK).body(ticketService.postpone(id));
    }

    @PreAuthorize("@securityHelper.isLoggedInUser(@ticketRepository.findUsernameById(#id).orElseThrow())")
    @PutMapping("/cancel")
    public ResponseEntity<ServiceResponse> cancel(@RequestParam UUID id) {
        return ResponseBuilder.status(HttpStatus.OK).body(ticketService.cancel(id));
    }

}
