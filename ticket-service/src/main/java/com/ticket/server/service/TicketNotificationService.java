package com.ticket.server.service;

import com.ticket.server.entity.Ticket;

public interface TicketNotificationService {

    void sendToQueue(Ticket ticket);
}
