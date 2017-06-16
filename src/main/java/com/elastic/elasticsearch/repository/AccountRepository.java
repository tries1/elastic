package com.elastic.elasticsearch.repository;

import com.elastic.elasticsearch.entity.Account;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by glenn on 2017. 5. 30..
 */
public interface AccountRepository extends ElasticsearchRepository<Account, Long>{

    List<Account> findByName(String name);

    Account findByEmail(String email);
}
