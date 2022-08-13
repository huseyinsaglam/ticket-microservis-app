package com.ticketservice.ticketservice.repository.elasticsearchrepo;

import com.ticketservice.ticketservice.entity.elasticsearchmodel.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel,String> {
}
