package com.elastic.service;

import com.elastic.domain.dto.MusicDTO;
import com.elastic.elasticsearch.entity.Music;
import com.elastic.elasticsearch.repository.MusicRepository;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * Created by glenn on 2017. 5. 31..
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicService {
    private final MusicRepository musicRepository;
    private final WeatherService weatherService;
    private final ElasticsearchTemplate elasticsearchTemplate;

    public List<Music> findAllMusic(){
        return StreamSupport.stream(musicRepository.findAll(new Sort(Sort.Direction.DESC, "id")).spliterator(), false).collect(Collectors.toList());
    }

    public List<Music> findMusicByGenre(String genre){
        return musicRepository.findByGenre(genre);
    }

    public Long saveMusic(MusicDTO musicDTO){
        Music music = new Music();
        music.setId(musicDTO.getId());
        music.setTitle(musicDTO.getTitle());
        music.setArtist(musicDTO.getArtist());
        music.setGenre(musicDTO.getGenre());
        music.setLike(musicDTO.getLike());
        return musicRepository.save(music).getId();
    }

    public List<Music> findMusicInAccordanceWeather(String cityName){
        String weather = weatherService.getCurrentWeatherByCityName(cityName);
        String genre = weatherMappingGenre(weather);
        String[] searchTitle = weatherMappingKeyward(weather);

        log.info(weather);
        log.info(genre);
        log.info(searchTitle[0] + ", " + searchTitle[1] + ", " + searchTitle[2]);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("genre", genre))
                .withFilter(QueryBuilders.termsQuery("title", searchTitle))
                .withSort(SortBuilders.fieldSort("like").order(SortOrder.DESC))
                //.withFilter(QueryBuilders.boolQuery().must(QueryBuilders.termsQuery("title", searchTitle)))
                .withPageable(new PageRequest(0, 100))
                .build();

        //return musicRepository.search(searchQuery).getContent();
        return elasticsearchTemplate.queryForList(searchQuery, Music.class);
    }

    private String weatherMappingGenre(String weather){
        String genre;
        switch(weather) {
        case "비":
            genre = "ballard";
            break;

        case "맑음":
            genre = "dance";
            break;

        case "Extreme":
            genre = "electronica";
            break;

        case "이슬비":
            genre = "fork";
            break;

        case "안개":
            genre = "hiphop";
            break;

        case "눈":
            genre = "R&B";
            break;

        case "폭우":
            genre = "rock";
            break;

        case "구름":
            genre = "fork";
            break;

        default:
            genre = "dance";
            break;
        }

        return genre;
    }

    private String[] weatherMappingKeyward(String weather){

        String[] strings;
        switch(weather) {
        case "비"://ballard
            strings = new String[] {"이슬","새벽","밤"};
            break;

        case "맑음"://dance
            strings = new String[] {"아침","태양","바람","햇살","사랑","good"};
            break;

        case "Extreme"://electronica
            strings = new String[] {"EDM", "DJ", "MIX"};
            break;

        case "이슬비"://fork
            strings = new String[] {"눈물", "비", "이별", "그대"};
            break;

        case "안개"://hiphop
            strings = new String[] {"fog", "feat", "swag"};
            break;

        case "눈"://R&B
            strings = new String[] {"별", "하늘", "공기"};
            break;

        case "폭우"://rock
            strings = new String[] {"storm", "폭풍", "번개"};
            break;

        case "구름"://fork
            strings = new String[] {"gray", "구름", "cloud", "거리", "바람"};
            break;

        default:
            strings = new String[] {"아침","화창한","바람"};
            break;
        }

        return strings;
    }
}
