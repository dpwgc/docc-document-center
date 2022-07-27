package com.dpwgc.document.query.ui.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearch 客户端配置
 */
@Configuration
public class ESConfig {

    @Value("${elasticsearch.hostname}")
    private String hostname;

    @Value("${elasticsearch.port}")
    private Integer port;

    @Value("${elasticsearch.scheme}")
    private String scheme;

    //注入IOC容器
    @Bean
    public ElasticsearchClient elasticsearchClient(){
        RestClient client = RestClient.builder(new HttpHost(hostname, port,scheme)).build();
        ElasticsearchTransport transport = new RestClientTransport(client,new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
}
