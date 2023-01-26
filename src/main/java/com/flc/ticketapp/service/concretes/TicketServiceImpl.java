package com.flc.ticketapp.service.concretes;

import com.flc.ticketapp.domain.entities.concretes.Ticket;
import com.flc.ticketapp.repository.abstracts.TicketRepository;
import com.flc.ticketapp.service.abstracts.TicketService;
import com.flc.ticketapp.service.helper.EntityServiceHelper;
import com.flc.ticketapp.service.model.request.ticket.TicketCreateRequestModel;
import com.flc.ticketapp.service.objectmapping.manual.concretes.ticket.TicketCreateMapper;
import com.flc.ticketapp.service.response.abstraction.ServiceResponse;
import com.flc.ticketapp.service.response.helper.ResponseHelper;
import com.flc.ticketapp.service.response.success.ServiceSuccessDataResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketCreateMapper ticketCreateMapper;

    @Override
    public ServiceResponse create(TicketCreateRequestModel model) {
        Ticket saved = EntityServiceHelper.saveAndRefresh(ticketRepository, ticketCreateMapper.map(model));
        return new ServiceSuccessDataResponse<>(saved, "Başarılı");
    }

    @Override
    public ServiceResponse postpone(UUID id) {
        return ResponseHelper.getResponseBySuccess(ticketRepository.postpone(id), "Ertelendi");
    }

    @Override
    public ServiceResponse cancel(UUID id) {
        return ResponseHelper.getResponseBySuccess(ticketRepository.cancel(id), "İptal edildi");
    }

}
