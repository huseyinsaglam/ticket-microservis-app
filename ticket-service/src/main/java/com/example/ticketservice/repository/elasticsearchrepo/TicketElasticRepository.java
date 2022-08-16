package com.example.ticketservice.repository.elasticsearchrepo;

import com.example.ticketservice.entity.elasticsearchmodel.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel,String> {
}
