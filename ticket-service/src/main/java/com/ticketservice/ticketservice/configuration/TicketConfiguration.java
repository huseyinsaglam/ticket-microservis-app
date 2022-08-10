package com.ticketservice.ticketservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TicketConfiguration {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
