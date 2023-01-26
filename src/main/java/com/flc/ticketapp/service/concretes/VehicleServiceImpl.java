package com.flc.ticketapp.service.concretes;

import com.flc.ticketapp.domain.entities.concretes.Vehicle;
import com.flc.ticketapp.repository.abstracts.VehicleRepository;
import com.flc.ticketapp.service.abstracts.VehicleService;
import com.flc.ticketapp.service.helper.EntityServiceHelper;
import com.flc.ticketapp.service.model.request.vehicle.VehicleCreateRequestModel;
import com.flc.ticketapp.service.objectmapping.manual.concretes.vehicle.VehicleCreateMapper;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import com.flc.ticketapp.service.response.helper.ResponseHelper;
import com.flc.ticketapp.service.response.success.ServiceSuccessDataResponse;
import com.flc.ticketapp.service.response.success.ServiceSuccessResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleCreateMapper vehicleCreateMapper;

    @Override
    public ServiceResponse create(VehicleCreateRequestModel model) {
        Vehicle saved = EntityServiceHelper.saveAndRefresh(vehicleRepository, vehicleCreateMapper.map(model));
        return new ServiceSuccessDataResponse<>(saved, "Başarılı");
    }

    @Override
    public ServiceResponse updateCapacity(UUID id, Integer capacity) {
        return ResponseHelper.getResponseBySuccess(vehicleRepository.updateCapacity(id, capacity), "Güncellendi");

    }

    @Override
    public ServiceResponse updateDateTime(UUID id, LocalDateTime dateTime) {
        return ResponseHelper.getResponseBySuccess(vehicleRepository.updateDateTime(id, dateTime), "Güncellendi");
    }

    @Override
    public ServiceResponse updatePrice(UUID id, Double price) {
        return ResponseHelper.getResponseBySuccess(vehicleRepository.updatePrice(id, price), "Güncellendi");
    }

    @Override
    public ServiceResponse delete(UUID id) {
        vehicleRepository.deleteById(id);
        return new ServiceSuccessResponse("Silindi");
    }

}
