package com.huey.learning.elasticsearch.rest.high.springboot.sample;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
class ElasticsearchRestHighWithSpringbootSampleApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void testGet() throws IOException {

        GetRequest getRequest = new GetRequest("bank", "1");

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        log.info("{}", getResponse);

    }

}
