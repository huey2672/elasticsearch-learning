package com.huey.learning.elasticsearch.springboot.repository.sample.repository;

import com.huey.learning.elasticsearch.springboot.repository.sample.entity.Bank;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huey
 */
@Repository
public interface BankRepository extends ElasticsearchRepository<Bank, Long> {
}
