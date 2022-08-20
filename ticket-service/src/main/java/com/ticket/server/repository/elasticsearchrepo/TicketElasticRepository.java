package com.ticket.server.repository.elasticsearchrepo;

import com.ticket.server.entity.elasticsearchmodel.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel,String> {
}
