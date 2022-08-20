package com.ticket.server.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.ticket.server")
@EnableElasticsearchRepositories
@ComponentScan(basePackages = {"com.ticket.server"})
public class TicketConfiguration {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
