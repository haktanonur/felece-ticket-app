package com.flc.ticketapp.service.abstracts;

import com.flc.ticketapp.service.model.request.admin.AdminCreateRequestModel;
import com.flc.ticketapp.service.model.request.user.UserCreateRequestModel;
import com.flc.ticketapp.service.model.response.admin.AdminResponseModel;
import com.flc.ticketapp.service.response.abstraction.ServiceDataResponse;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;

public interface AdminService {

    ServiceDataResponse<AdminResponseModel> create(AdminCreateRequestModel model);

}
