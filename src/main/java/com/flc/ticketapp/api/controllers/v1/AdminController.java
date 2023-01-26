package com.flc.ticketapp.api.controllers.v1;

import com.flc.ticketapp.api.common.Origin;
import com.flc.ticketapp.api.common.ResponseBuilder;
import com.flc.ticketapp.api.security.JwtTokenHelper;
import com.flc.ticketapp.domain.constants.RoleConst;
import com.flc.ticketapp.service.abstracts.AdminService;
import com.flc.ticketapp.service.model.request.admin.AdminCreateRequestModel;
import com.flc.ticketapp.service.response.abstraction.ServiceDataResponse;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Validated

@CrossOrigin(origins = Origin.LOCALHOST_3000)
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private final JwtTokenHelper jwtTokenHelper;

    @Secured(RoleConst.Name.ADMIN)
    @PostMapping("/create")
    public ResponseEntity<ServiceResponse> create(@RequestBody @Valid AdminCreateRequestModel model) {
        ServiceDataResponse<?> serviceResponse = adminService.create(model);
        return ResponseBuilder.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, jwtTokenHelper.generateToken(serviceResponse.getData()))
                .body(serviceResponse);
    }

}
