package com.elastic;

import com.elastic.elasticsearch.entity.Account;
import com.elastic.elasticsearch.repository.AccountRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by glenn on 2017. 5. 30..
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ElasticApplication.class)
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void saveTest(){
        accountRepository.save(new Account("test", "test@email.com"));
    }

    @Test
    public void findAllTest(){
        accountRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void findOneTest(){
        List<Account> list = accountRepository.findByName("test");
        list.forEach(System.out::println);
    }
}
