package com.elastic.elasticsearch.repository;

import com.elastic.elasticsearch.entity.Music;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by glenn on 2017. 5. 30..
 */
public interface MusicRepository extends ElasticsearchRepository<Music, Long>{
    List<Music> findByGenre(String genre);
    List<Music> findByGenreAndTitleOrTitleOrTitle(String genre, String title1, String title2, String title3);
}
