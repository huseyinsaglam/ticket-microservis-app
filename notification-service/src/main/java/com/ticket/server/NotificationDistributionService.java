package com.ticket.server;

import com.ticket.server.messaging.TicketNotification;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)          // kuyrugu dinleyen class ın anatasyonu olması gerekiyor
public class NotificationDistributionService {

    @StreamListener(Sink.INPUT)
    public void onNotification(TicketNotification ticketNotification){
        System.out.println("———————————————————————————————————————————");
        System.out.println("Notification Alindi Kullanicilara gönderiliyor.");
        System.out.println("Notification -> " + ticketNotification.toString());
    }
}
