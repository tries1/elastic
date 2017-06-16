package com.elastic.controller;

import com.elastic.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {
    private final WeatherService weatherService;

    @CrossOrigin
    @GetMapping("current/{cityName}")
    private Object currentWeather(@PathVariable String cityName){
        return weatherService.getCurrentWeatherByCityName(cityName);
    }
}
