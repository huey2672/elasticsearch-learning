package com.huey.learning.elasticsearch.rest.template.sample;

import com.huey.learning.elasticsearch.rest.template.sample.entity.Bank;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

@Slf4j
@SpringBootTest
class ElasticsearchRestTemplateSampleApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void testIndex() {

        Bank bank = new Bank();
        bank.setAccountNumber(13L);
        bank.setBalance(32838L);
        bank.setFirstName("Nanette");
        bank.setLastName("Bates");
        bank.setAge(28L);
        bank.setGender("F");
        bank.setAddress("789 Madison Street");
        bank.setEmployer("Quility");
        bank.setEmail("nanettebates@quility.com");
        bank.setCity("Nogal");
        bank.setState("VA");

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(bank)
                .build();

        elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of("bank"));

    }

    @Test
    void testGet() {

        Bank bank = elasticsearchRestTemplate.get("13", Bank.class);

        log.info("{}", bank);

    }

    @Test
    void testUpdate() {

        Document document = Document.create().append("address", "999 Madison Street");

        UpdateQuery updateQuery = UpdateQuery.builder("13")
                .withDocument(document)
                .build();

        elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of("bank"));

    }

    @Test
    void testDelete() {

        elasticsearchRestTemplate.delete("13", IndexCoordinates.of("bank"));

    }

    @Test
    void testSearch() {

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("address", "Avenue"))
                .build();
        SearchHits<Bank> searchHits = elasticsearchRestTemplate.search(searchQuery, Bank.class, IndexCoordinates.of("bank"));

        searchHits.get().forEach(searchHit -> {
            Bank bank = searchHit.getContent();
            log.info("{}", bank);
        });

    }

}
