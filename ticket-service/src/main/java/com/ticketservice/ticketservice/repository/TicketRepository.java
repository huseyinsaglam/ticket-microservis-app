package com.ticketservice.ticketservice.repository;

import com.ticketservice.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,String> {
}
