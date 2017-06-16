package com.elastic.service;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by glenn on 2017. 5. 31..
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherService {
    private final RestTemplate restTemplate;

    @Value("${weather.current.api}")
    private String weatherApiUri;

    public String getCurrentWeatherByCityName(String cityName){
        JsonNode json = restTemplate.getForObject(String.format(weatherApiUri, cityName), JsonNode.class);


        String weatherCode = Optional.ofNullable(json.get("weather").get(0).get("id").toString()).orElse("9");
        String weatherName;

        if("800".equals(weatherCode)){
            weatherName = getWeatherName(Integer.parseInt(weatherCode));
        }else{
            weatherName = getWeatherName(Integer.parseInt(weatherCode.substring(0, 1)));
        }


        log.debug("weatherCode : {}", weatherCode);
        log.debug("weather main : {}", Optional.ofNullable(json.get("weather").get(0).get("main").toString()).orElse("not found"));
        return weatherName;
    }

    private String getWeatherName(int num){
        String result;
        switch(num) {
        case 2:
            //result = "Thunderstorm";//폭우
            result = "폭우";
            break;

        case 3:
            //result = "Drizzle";//이슬비
            result = "이슬비";
            break;

        case 5:
            //result = "Rain";//비
            result = "비";
            break;

        case 6:
            //result = "Snow";//눈
            result = "눈";
            break;

        case 7:
            //result = "Atmosphere";//먼지나 가스 안개
            result = "안개";
            break;

        case 8:
            //result = "Clouds";//구름 많음
            result = "구름";
            break;

        case 800:
            //result = "Clear";//맑음
            result = "맑음";
            break;

        case 9:
            result = "Extreme";//허리케인, 해일 토네이도 매우 뜨거운 같은 극단적인 날씨
            break;

        default:
            //result = "Clear";//기본값은 그냥 맑음으로..
            result = "맑음";//기본값은 그냥 맑음으로..
            break;
        }

        return result;
    }
}
