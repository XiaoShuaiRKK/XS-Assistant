package com.xs.assistant.search.Config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class ElasticSearchConfig {
//    @Bean
//    public ElasticsearchClient elasticsearchClient(){
//        RestClient client = RestClient.builder(new HttpHost("127.0.0.1",9200,"http")).build();
//        RestClientTransport transport = new RestClientTransport(client, new JacksonJsonpMapper());
//        return new ElasticsearchClient(transport);
//    }
}
