package com.flc.ticketapp.api.controllers.v1;

import com.flc.ticketapp.api.common.Origin;
import com.flc.ticketapp.api.common.ResponseBuilder;
import com.flc.ticketapp.domain.constants.RoleConst;
import com.flc.ticketapp.service.abstracts.VehicleService;
import com.flc.ticketapp.service.model.request.vehicle.VehicleCreateRequestModel;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Validated

@CrossOrigin(origins = Origin.LOCALHOST_3000)
@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Secured(RoleConst.Name.ADMIN)
    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> create(@RequestBody @Valid VehicleCreateRequestModel model) {
        return ResponseBuilder.status(HttpStatus.CREATED).body(vehicleService.create(model));
    }

    @Secured(RoleConst.Name.ADMIN)
    @PutMapping("/update/capacity")
    public ResponseEntity<ServiceResponse> updateCapacity(@RequestParam UUID id, @RequestParam Integer capacity) {
        return ResponseBuilder.status(HttpStatus.OK).body(vehicleService.updateCapacity(id, capacity));
    }

    @Secured(RoleConst.Name.ADMIN)
    @PutMapping("/update/date_time")
    public ResponseEntity<ServiceResponse> updateDateTime(@RequestParam UUID id, @RequestParam LocalDateTime dateTime) {
        return ResponseBuilder.status(HttpStatus.OK).body(vehicleService.updateDateTime(id, dateTime));
    }

    @Secured(RoleConst.Name.ADMIN)
    @PutMapping("/update/price")
    public ResponseEntity<ServiceResponse> updatePrice(@RequestParam UUID id, @RequestParam Double price) {
        return ResponseBuilder.status(HttpStatus.OK).body(vehicleService.updatePrice(id, price));
    }

    @Secured(RoleConst.Name.ADMIN)
    @DeleteMapping("/delete")
    public ResponseEntity<ServiceResponse> delete(@RequestParam UUID id) {
        return ResponseBuilder.status(HttpStatus.OK).body(vehicleService.delete(id));
    }

}

