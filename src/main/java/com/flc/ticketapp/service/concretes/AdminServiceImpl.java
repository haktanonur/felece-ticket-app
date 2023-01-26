package com.flc.ticketapp.service.concretes;

import com.flc.ticketapp.domain.entities.concretes.Admin;
import com.flc.ticketapp.repository.abstracts.AdminRepository;
import com.flc.ticketapp.service.abstracts.AdminService;
import com.flc.ticketapp.service.helper.EntityServiceHelper;
import com.flc.ticketapp.service.model.request.admin.AdminCreateRequestModel;
import com.flc.ticketapp.service.model.response.admin.AdminResponseModel;
import com.flc.ticketapp.service.objectmapping.manual.concretes.admin.AdminCreateMapper;
import com.flc.ticketapp.service.objectmapping.manual.concretes.admin.AdminResponseMapper;
import com.flc.ticketapp.service.response.abstraction.ServiceDataResponse;
import com.flc.ticketapp.service.response.success.ServiceSuccessDataResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminCreateMapper adminCreateMapper;
    private final AdminResponseMapper adminResponseMapper;

    public ServiceDataResponse<AdminResponseModel> create(AdminCreateRequestModel model) {
        Admin saved = EntityServiceHelper.saveAndRefresh(adminRepository, adminCreateMapper.map(model));
        return new ServiceSuccessDataResponse<>(adminResponseMapper.map(saved), "Başarılı");
    }

    /*
    @Override
    public ServiceResponse create(UserCreateRequestModel model) {
        Admin admin = new Admin();
        admin.setUsername(model.getUsername());
        admin.setPassword(model.getPassword());
        admin.setRoles(Set.of(new Role(null, "ROLE_ADMIN")));
        Admin saved = adminRepository.saveAndFlush(admin);
        entityManager.refresh(saved);
        AdminResponseModel responseModel = new AdminResponseModel();
        responseModel.setUsername(saved.getUsername());
        responseModel.setPassword(saved.getUsername());
        responseModel.setRoles(new ArrayList<>());
        for (Role role : saved.getRoles()) {
            RoleResponseModel roleResponseModel = new RoleResponseModel();
            roleResponseModel.setId(role.getId());
            roleResponseModel.setName(role.getName());
            responseModel.getRoles().add(roleResponseModel);
        }
        return new ServiceSuccessDataResponse<>(responseModel, "Başarılı");
    }
    */

}
