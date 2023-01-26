package com.flc.ticketapp.service.response.error.suberrors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flc.ticketapp.service.response.abstraction.ServiceSubError;

public record ServiceValidationError(
        String field,
        @JsonFormat(shape = JsonFormat.Shape.STRING) Object rejectedValue,
        String code,
        String message) implements ServiceSubError {
}
