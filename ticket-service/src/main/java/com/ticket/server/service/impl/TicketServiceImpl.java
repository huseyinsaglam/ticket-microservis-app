package com.ticket.server.service.impl;

import com.ticket.server.client.AccountSrvcClient;
import com.ticket.server.contract.AccountDto;
import com.ticket.server.dto.TicketDto;
import com.ticket.server.entity.PriorityType;
import com.ticket.server.entity.Ticket;
import com.ticket.server.entity.TicketStatus;
import com.ticket.server.entity.elasticsearchmodel.TicketModel;
import com.ticket.server.repository.TicketRepository;
import com.ticket.server.repository.elasticsearchrepo.TicketElasticRepository;
import com.ticket.server.service.TicketNotificationService;
import com.ticket.server.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketElasticRepository ticketElasticRepository;
    private final TicketRepository ticketRepository;
    private final TicketNotificationService ticketNotificationService;
    private final AccountSrvcClient accountSrvcClient;

    public TicketServiceImpl(TicketElasticRepository ticketElasticRepository,
                             TicketRepository ticketRepository,
                             TicketNotificationService ticketNotificationService,
                             AccountSrvcClient accountSrvcClient) {
        this.ticketElasticRepository = ticketElasticRepository;
        this.ticketRepository = ticketRepository;
        this.ticketNotificationService = ticketNotificationService;
        this.accountSrvcClient = accountSrvcClient;
    }

    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        // Ticket Entity
        if (ticketDto.getDescription() == null)
            throw new IllegalArgumentException("Description bos olamaz");

        Ticket ticket = new Ticket();

        ResponseEntity<AccountDto> accountDtoResponseEntity= accountSrvcClient.get(ticketDto.getAssignee());
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
