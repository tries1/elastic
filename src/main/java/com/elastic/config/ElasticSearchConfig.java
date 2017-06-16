package com.elastic.config;

import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.UnknownHostException;

/**
 * Created by glenn on 2017. 5. 30..
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com/elastic/elasticsearch/repository")
public class ElasticSearchConfig {


    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException{
        /*String clusterName = "elastic";
        String server = "localhost";
        Integer port = 9300;
        Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
        InetSocketTransportAddress inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName(server), port);
        return new ElasticsearchTemplate(TransportClient.builder().settings(settings).build().addTransportAddress(inetSocketTransportAddress));*/
        return new ElasticsearchTemplate(NodeBuilder.nodeBuilder().local(true).node().client());
    }
}
