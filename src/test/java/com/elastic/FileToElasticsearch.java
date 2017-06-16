package com.elastic;

import com.elastic.domain.dto.MusicDTO;
import com.elastic.service.MusicService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by glenn on 2017. 6. 7..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ElasticApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileToElasticsearch {

    @Autowired
    private MusicService musicService;

    @Test
    public void fileToElasticsearch() throws Exception {
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.set(3000 * 8);
        String genre = "rock";
        BufferedReader br = new BufferedReader(new FileReader(String.format("/Users/glenn/dev/crawling/doc/%s.csv", genre)));
        try {
            String line;

            while ((line = br.readLine()) != null) {

                try {
                    String[] arr = line.split("\\|\\|");
                    System.out.println(String.format("%s, %s, %s, %s", arr[0], arr[1], arr[2], arr[3]));

                    MusicDTO musicDTO = new MusicDTO();
                    musicDTO.setId(atomicLong.incrementAndGet());
                    musicDTO.setTitle(arr[0]);
                    musicDTO.setArtist(arr[1]);
                    musicDTO.setGenre(arr[2]);
                    musicDTO.setLike(Long.parseLong(arr[3]));

                    musicService.saveMusic(musicDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
            if (br != null) { try { br.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
    }
}
