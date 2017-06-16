package com.elastic.controller;

import com.elastic.domain.dto.MusicDTO;
import com.elastic.service.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by glenn on 2017. 5. 30..
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "api/music", produces = MediaType.APPLICATION_JSON_VALUE)
public class MusicController {
    private final MusicService musicService;

    @GetMapping("/")
    private Object getAllMusic(){
        return musicService.findAllMusic();
    }


    @GetMapping("/{genre}")
    private Object getMusicByGenre(@PathVariable String genre){
        return musicService.findMusicByGenre(genre);
    }
    @PostMapping(value = "/", consumes = MediaType.ALL_VALUE)
    private Object saveMusic(@RequestBody MusicDTO musicDTO){
        return musicService.saveMusic(musicDTO);
    }


    @CrossOrigin
    @GetMapping(value = "/weather/{cityName}")
    private Object getMusicInAccordanceWeather(@PathVariable String cityName){
        return musicService.findMusicInAccordanceWeather(cityName);
    }
}
