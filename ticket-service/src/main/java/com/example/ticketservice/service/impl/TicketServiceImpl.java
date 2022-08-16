package com.example.ticketservice.service.impl;

import com.example.servicecommon.client.AccountServiceClient;
import com.example.servicecommon.contract.AccountDto;
import com.example.ticketservice.service.TicketService;
import com.example.ticketservice.dto.TicketDto;
import com.example.ticketservice.entity.PriorityType;
import com.example.ticketservice.entity.Ticket;
import com.example.ticketservice.entity.TicketStatus;
import com.example.ticketservice.entity.elasticsearchmodel.TicketModel;
import com.example.ticketservice.repository.TicketRepository;
import com.example.ticketservice.repository.elasticsearchrepo.TicketElasticRepository;
import com.example.ticketservice.service.TicketNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketElasticRepository ticketElasticRepository;
    private final TicketRepository ticketRepository;
    private final TicketNotificationService ticketNotificationService;
    private final AccountServiceClient accountServiceClient;

    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        // Ticket Entity
        if (ticketDto.getDescription() == null)
            throw new IllegalArgumentException("Description bos olamaz");

        Ticket ticket = new Ticket();

        ResponseEntity<AccountDto> accountDtoResponseEntity= accountServiceClient.get(ticket.getAssignee());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
        ticket.setAssignee(accountDtoResponseEntity.getBody().getId());


        // mysql kaydet
        ticket = ticketRepository.save(ticket);


        // TicketModel nesnesi yarat
        TicketModel model = TicketModel.builder()
                .description(ticket.getDescription())
                .notes(ticket.getNotes())
                .id(ticket.getId())
                .priorityType(ticket.getPriorityType().getLabel())
                .ticketStatus(ticket.getTicketStatus().getLabel())
                .assignee(accountDtoResponseEntity.getBody().getNameSurname())
                .ticketDate(ticket.getTicketDate()).build();

        // elastic kaydet
        ticketElasticRepository.save(model);

        // olusan nesneyi döndür
        ticketDto.setId(ticket.getId());

        // kuyruga notification yaz
        ticketNotificationService.sendToQueue(ticket);

        return ticketDto;
    }

    @Override
    public TicketDto update(String id, TicketDto ticketDto) {
        return null;
    }

    @Override
    public TicketDto getById(String ticketId) {
        return null;
    }

    @Override
    public Page<TicketDto> getPagination(Pageable pageable) {
        return null;
    }
}
