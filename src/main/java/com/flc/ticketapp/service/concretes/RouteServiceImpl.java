package com.flc.ticketapp.service.concretes;

import com.flc.ticketapp.domain.entities.concretes.Route;
import com.flc.ticketapp.repository.abstracts.RouteRepository;
import com.flc.ticketapp.service.abstracts.RouteService;
import com.flc.ticketapp.service.helper.EntityServiceHelper;
import com.flc.ticketapp.service.model.request.route.RouteCreateRequestModel;
import com.flc.ticketapp.service.objectmapping.manual.concretes.route.RouteCreateMapper;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import com.flc.ticketapp.service.response.helper.ResponseHelper;
import com.flc.ticketapp.service.response.success.ServiceSuccessDataResponse;
import com.flc.ticketapp.service.response.success.ServiceSuccessResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final RouteCreateMapper routeCreateMapper;

    @Override
    public ServiceResponse create(RouteCreateRequestModel model) {
        Route saved = EntityServiceHelper.saveAndRefresh(routeRepository, routeCreateMapper.map(model));
        return new ServiceSuccessDataResponse<>(saved, "Başarılı");
    }

    @Override
    public ServiceResponse updateFrom(UUID id, String from) {
        return ResponseHelper.getResponseBySuccess(routeRepository.updateFrom(id, from), "Güncellendi");
    }

    @Override
    public ServiceResponse updateTo(UUID id, String to) {
        return ResponseHelper.getResponseBySuccess(routeRepository.updateTo(id, to), "Güncellendi");
    }

    @Override
    public ServiceResponse delete(UUID id) {
        routeRepository.deleteById(id);
        return new ServiceSuccessResponse("Silindi");
    }

}
