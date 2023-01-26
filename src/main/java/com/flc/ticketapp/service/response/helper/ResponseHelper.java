package com.flc.ticketapp.service.response.helper;

import com.flc.ticketapp.domain.constants.MsgCode;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import com.flc.ticketapp.service.response.error.ServiceErrorResponse;
import com.flc.ticketapp.service.response.success.ServiceSuccessResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseHelper {

    public static ServiceResponse getResponseBySuccess(boolean success, String successMsgCode) {
        if (success) return new ServiceSuccessResponse(successMsgCode);
        return new ServiceErrorResponse(MsgCode.COMMON_ERROR_INVALID);
    }

    public static ServiceResponse getResponseBySuccess(int numOfRowsToBeUpdated, String successMsgCode) {
        return getResponseBySuccess(numOfRowsToBeUpdated > 0, successMsgCode);
    }

}
