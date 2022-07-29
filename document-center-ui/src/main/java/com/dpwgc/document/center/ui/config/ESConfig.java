package com.dpwgc.document.center.ui.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearch 客户端配置
 */
@Configuration
public class ESConfig {

    @Value("${elasticsearch.url}")
    private String url;

    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private String password;

    //注入IOC容器
    @Bean
    public ElasticsearchClient elasticsearchClient(){

        //解析url
        String[] urls = url.split(",");
        HttpHost[] httpHosts = new HttpHost[urls.length];
        //转换成HttpHost数组
        for (int i=0;i<urls.length;i++) {
            httpHosts[i] = new HttpHost(urls[i]);
        }

        //配置客户端密码验证
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));  //es账号密码 (默认用户名为elastic)

        //配置客户端
        RestClient client = RestClient.builder(httpHosts).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.disableAuthCaching();
                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        }).build();

        ElasticsearchTransport transport = new RestClientTransport(client,new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
}
