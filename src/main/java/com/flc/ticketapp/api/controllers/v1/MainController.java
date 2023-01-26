package com.flc.ticketapp.api.controllers.v1;

import com.flc.ticketapp.api.common.ResponseBuilder;
import com.flc.ticketapp.service.response.error.ServiceErrorResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class MainController implements ErrorController {

    @RequestMapping("/")
    public void redirectToSwaggerUI(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html#");
    }

    @RequestMapping("/error")
    public ResponseEntity<ServiceErrorResponse> handleError(HttpServletRequest request) {
        HttpStatus httpStatus = Optional
                .of(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
                .flatMap(sub -> Optional.of(sub.toString()))
                .flatMap(sub -> Optional.of(Integer.parseInt(sub)))
                .map(HttpStatus::resolve)
                .orElse(HttpStatus.BAD_REQUEST);
        return ResponseBuilder.status(httpStatus)
                .body(new ServiceErrorResponse());
    }

}
