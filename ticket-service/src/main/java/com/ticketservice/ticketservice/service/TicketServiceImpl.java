package com.ticketservice.ticketservice.service;

import com.ticketservice.ticketservice.dto.TicketDto;
import com.ticketservice.ticketservice.entity.PriorityType;
import com.ticketservice.ticketservice.entity.Ticket;
import com.ticketservice.ticketservice.entity.TicketStatus;
import com.ticketservice.ticketservice.entity.elasticsearchmodel.TicketModel;
import com.ticketservice.ticketservice.repository.TicketRepository;
import com.ticketservice.ticketservice.repository.elasticsearchrepo.TicketElasticRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketElasticRepository ticketElasticRepository;
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        // Ticket Entity
        if (ticketDto.getDescription() == null)
            throw new IllegalArgumentException("Description bos olamaz");

        Ticket ticket = new Ticket();

        ticket.setDescription(ticketDto.getDescription());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));

        // mysql kaydet
        ticket = ticketRepository.save(ticket);


        // TicketModel nesnesi yarat
        TicketModel model = TicketModel.builder()
                .description(ticket.getDescription())
                .notes(ticket.getNotes())
                .id(ticket.getId())
                .priorityType(ticket.getPriorityType().getLabel())
                .ticketStatus(ticket.getTicketStatus().getLabel())
                .ticketDate(ticket.getTicketDate()).build();

        // elastic kaydet
        ticketElasticRepository.save(model);

        // olusan nesneyi döndür
        ticketDto.setId(ticket.getId());

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
