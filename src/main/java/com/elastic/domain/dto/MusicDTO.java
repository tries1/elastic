package com.elastic.domain.dto;

import lombok.Data;

/**
 * Created by glenn on 2017. 6. 7..
 */
@Data
public class MusicDTO {
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private Long like;
}
