package com.ticketservice.ticketservice.service;

import com.ticketservice.ticketservice.dto.TicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class TicketServiceImpl implements TicketService{
    @Override
    public TicketDto save(TicketDto ticketDto) {
        return null;
    }

    @Override
    public TicketDto update(TicketDto ticketDto) {
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
