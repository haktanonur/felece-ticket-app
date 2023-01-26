package com.flc.ticketapp.service.response.error;

import com.flc.ticketapp.domain.constants.MsgCode;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;

public class ServiceErrorResponse extends ServiceResponse {

    public ServiceErrorResponse(String messageCode, Object... args) {
        super(messageCode, args);
    }

    public ServiceErrorResponse(String messageCode) {
        this(messageCode, (Object[]) null);
    }

    public ServiceErrorResponse() {
        this(MsgCode.COMMON_ERROR, (Object[]) null);
    }

}
