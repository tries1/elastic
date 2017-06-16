package com.elastic.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by glenn on 2017. 5. 30..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "accounts", type = "account", shards = 1, replicas = 0, refreshInterval = "-1")
public class Account {

    @Id
    private Long id;

    private String name;

    private String email;

    public Account(String name, String email){
        this.name = name;
        this.email = email;

    }
}
