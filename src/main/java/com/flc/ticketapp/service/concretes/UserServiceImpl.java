package com.flc.ticketapp.service.concretes;

import com.flc.ticketapp.domain.constants.MsgCode;
import com.flc.ticketapp.domain.entities.concretes.User;
import com.flc.ticketapp.repository.abstracts.UserRepository;
import com.flc.ticketapp.service.abstracts.UserService;
import com.flc.ticketapp.service.helper.EntityServiceHelper;
import com.flc.ticketapp.service.model.request.user.UserCreateRequestModel;
import com.flc.ticketapp.service.model.response.user.UserAuthProjection;
import com.flc.ticketapp.service.model.response.user.UserResponseModel;
import com.flc.ticketapp.service.objectmapping.manual.concretes.security.UserDetailsMapper;
import com.flc.ticketapp.service.objectmapping.manual.concretes.user.UserCreateMapper;
import com.flc.ticketapp.service.objectmapping.manual.concretes.user.UserResponseMapper;
import com.flc.ticketapp.service.response.abstraction.ServiceDataResponse;
import com.flc.ticketapp.service.response.success.ServiceSuccessDataResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;
    private final UserResponseMapper userResponseMapper;
    private final UserDetailsMapper userDetailsMapper;

    @Override
    public UserAuthProjection getUserAuthProjection(String username) {
        return userRepository.findUserAuthoritiesByUsername(username, UserAuthProjection.class).orElseThrow();
    }

    @Override
    public String getPasswordByUsername(String username) {
        return userRepository.findPasswordByUsername(username);
    }

    @Override
    public ServiceDataResponse<UserResponseModel> create(UserCreateRequestModel model) {
        User saved = EntityServiceHelper.saveAndRefresh(userRepository, userCreateMapper.map(model));
        return new ServiceSuccessDataResponse<>(userResponseMapper.map(saved), "Başarılı");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsMapper.map(userRepository.findUserAuthoritiesByUsername(username, User.class)
                .orElseThrow(() -> new BadCredentialsException(MsgCode.SECURITY_RE_LOGIN_MALFORMED_JWT_USERNAME)));

    }
}
