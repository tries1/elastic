package com.elastic.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by glenn on 2017. 5. 30..
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "music", type = "song", shards = 1, replicas = 0, refreshInterval = "-1")
public class Music {

    @Id
    private Long id;

    private String title;

    private String artist;

    private String genre;

    private Long like;
}
