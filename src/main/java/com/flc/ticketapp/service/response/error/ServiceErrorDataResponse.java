package com.flc.ticketapp.service.response.error;

import com.flc.ticketapp.domain.constants.MsgCode;
import com.flc.ticketapp.domain.helper.ExceptionTools;
import com.flc.ticketapp.service.response.abstraction.ServiceDataResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class ServiceErrorDataResponse<T> extends ServiceDataResponse<T> {

    private final String debugMessage;

    public ServiceErrorDataResponse(Throwable ex, String debugMessage, T data, String messageCode, Object... args) {
        super(data, messageCode == null ? MsgCode.COMMON_ERROR : messageCode, args);
        this.debugMessage = debugMessage;
        log.info("An exception has handled: " + ex.getClass().getName());
    }

    public ServiceErrorDataResponse(Throwable ex, String debugMessage, T data, String messageCode) {
        this(ex, debugMessage, data, messageCode, (Object[]) null);
    }

    public ServiceErrorDataResponse(Throwable ex, T data, String messageCode, Object... args) {
        this(ex, ExceptionTools.getRootCause(ex).getLocalizedMessage(), data, messageCode, args);
    }

    public ServiceErrorDataResponse(Throwable ex, T data, String messageCode) {
        this(ex, ExceptionTools.getRootCause(ex).getLocalizedMessage(), data, messageCode, (Object[]) null);
    }

    public ServiceErrorDataResponse(Throwable ex, String messageCode) {
        this(ex, ExceptionTools.getRootCause(ex).getLocalizedMessage(), null, messageCode, (Object[]) null);
    }

    public ServiceErrorDataResponse(Throwable ex) {
        this(ex, ExceptionTools.getRootCause(ex).getLocalizedMessage(), null, MsgCode.COMMON_ERROR, (Object[]) null);
    }

    public ServiceErrorDataResponse(String debugMessage, T data, String messageCode, Object... args) {
        this(new Throwable(), debugMessage, data, messageCode, args);
    }

}
