package com.flc.ticketapp.api.handlers;

import com.flc.ticketapp.api.common.ResponseBuilder;
import com.flc.ticketapp.domain.constants.MsgCode;
import com.flc.ticketapp.domain.helper.StringTools;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import com.flc.ticketapp.service.response.error.ServiceErrorDataResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
@ControllerAdvice
@Order(0)
public class PersistenceExceptionHandlers {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ServiceResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseBuilder.status(HttpStatus.BAD_REQUEST)
                .body(new ServiceErrorDataResponse<>(exception, exception.getMessage() == null ? MsgCode.COMMON_ERROR_ENTITY_NOT_FOUND : exception.getMessage()));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ServiceResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
        return ResponseBuilder.status(HttpStatus.BAD_REQUEST)
                .body(new ServiceErrorDataResponse<>(exception, MsgCode.COMMON_ERROR_ENTITY_NOT_FOUND));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ServiceResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        Optional<String> code = StringTools.extract(exception.getMessage(), "(fk|uk)_\\w+_\\w+").map(m -> "DB.Constraint." + m);
        return ResponseBuilder.status(HttpStatus.BAD_REQUEST)
                .body(new ServiceErrorDataResponse<>(exception, code.orElse(MsgCode.COMMON_ERROR_INVALID)));
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ServiceResponse> handlePersistenceException(PersistenceException exception) {
        return ResponseBuilder.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ServiceErrorDataResponse<>(exception, exception.getLocalizedMessage()));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ServiceResponse> handleSQLException(SQLException exception) {
        return ResponseBuilder.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ServiceErrorDataResponse<>(exception, exception.getLocalizedMessage()));
    }

}
