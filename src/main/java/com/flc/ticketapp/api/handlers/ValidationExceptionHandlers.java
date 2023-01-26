package com.flc.ticketapp.api.handlers;

import com.flc.ticketapp.api.common.ResponseBuilder;
import com.flc.ticketapp.domain.constants.MsgCode;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import com.flc.ticketapp.service.response.abstraction.ServiceSubError;
import com.flc.ticketapp.service.response.error.ServiceErrorDataResponse;
import com.flc.ticketapp.service.response.error.suberrors.ServiceValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@ControllerAdvice
@Order(0)
public class ValidationExceptionHandlers {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ServiceResponse> handleBindException(BindException ex) {
        List<ServiceSubError> errors = new ArrayList<>(ex.getErrorCount());
        for (FieldError fieldError : ex.getFieldErrors())
            errors.add(new ServiceValidationError(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getCode(), fieldError.getDefaultMessage()));
        for (ObjectError globalError : ex.getGlobalErrors())
            errors.add(new ServiceValidationError(null, null, globalError.getCode(), globalError.getDefaultMessage()));
        return ResponseBuilder.status(HttpStatus.BAD_REQUEST)
                .body(new ServiceErrorDataResponse<>(ex, errors, MsgCode.COMMON_ERROR_INVALID));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ServiceResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        ArrayList<ServiceSubError> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<?> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String field = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);
            errors.add(new ServiceValidationError(field, violation.getInvalidValue(), violation.getMessageTemplate(), violation.getMessage()));
        }
        return ResponseBuilder.status(HttpStatus.BAD_REQUEST)
                .body(new ServiceErrorDataResponse<>(ex, errors, MsgCode.COMMON_ERROR_INVALID));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ServiceResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseBuilder.status(HttpStatus.BAD_REQUEST)
                .body(new ServiceErrorDataResponse<>(ex, ex.getLocalizedMessage()));
    }

}
