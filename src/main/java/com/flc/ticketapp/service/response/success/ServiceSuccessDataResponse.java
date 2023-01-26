package com.flc.ticketapp.service.response.success;

import com.flc.ticketapp.domain.constants.MsgCode;
import com.flc.ticketapp.service.response.abstraction.ServiceDataResponse;

public class ServiceSuccessDataResponse<T> extends ServiceDataResponse<T> {

    public ServiceSuccessDataResponse(T data, String messageCode, Object... args) {
        super(data, messageCode, args);
    }

    public ServiceSuccessDataResponse(T data, String messageCode) {
        this(data, messageCode, (Object[]) null);
    }

    public ServiceSuccessDataResponse(T data) {
        this(data, MsgCode.COMMON_SUCCESS, (Object[]) null);
    }

}
