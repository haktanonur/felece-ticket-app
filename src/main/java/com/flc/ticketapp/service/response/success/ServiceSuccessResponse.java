package com.flc.ticketapp.service.response.success;

import com.flc.ticketapp.domain.constants.MsgCode;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;

public class ServiceSuccessResponse extends ServiceResponse {

    public ServiceSuccessResponse(String messageCode, Object... args) {
        super(messageCode, args);
    }

    public ServiceSuccessResponse(String messageCode) {
        this(messageCode, (Object[]) null);
    }

    public ServiceSuccessResponse() {
        this(MsgCode.COMMON_SUCCESS, (Object[]) null);
    }

}
