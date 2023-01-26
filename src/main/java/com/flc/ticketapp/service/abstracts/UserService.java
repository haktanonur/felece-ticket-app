package com.flc.ticketapp.service.abstracts;

import com.flc.ticketapp.service.model.request.user.UserCreateRequestModel;
import com.flc.ticketapp.service.model.response.user.UserAuthProjection;
import com.flc.ticketapp.service.model.response.user.UserResponseModel;
import com.flc.ticketapp.service.response.abstraction.ServiceDataResponse;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserAuthProjection getUserAuthProjection(String username);

    String getPasswordByUsername(String username);

    ServiceDataResponse<UserResponseModel> create(UserCreateRequestModel model);

}
