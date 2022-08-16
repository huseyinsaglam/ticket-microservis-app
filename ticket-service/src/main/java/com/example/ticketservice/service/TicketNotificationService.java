package com.example.ticketservice.service;

import com.example.ticketservice.entity.Ticket;

public interface TicketNotificationService {

    void sendToQueue(Ticket ticket);
}
