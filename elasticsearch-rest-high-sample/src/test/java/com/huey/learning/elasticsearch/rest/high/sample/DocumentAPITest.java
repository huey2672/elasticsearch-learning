package com.huey.learning.elasticsearch.rest.high.sample;

import com.alibaba.fastjson.JSON;
import com.huey.learning.elasticsearch.rest.high.sample.entity.Bank;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author huey
 */
@Slf4j
public class DocumentAPITest {

    private RestHighLevelClient client;

    @Before
    public void initClient() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        );
        client = new RestHighLevelClient(builder);
    }

    @After
    public void closeClient() throws IOException {
        if (client != null) {
            client.close();
        }
    }

    @Test
    public void testIndex() throws IOException {

        // create a document
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
        String bankDoc = JSON.toJSONString(bank);

        // new a index request
        IndexRequest indexRequest = new IndexRequest()
                // specify the index name
                .index("bank")
                // set the id of the indexed document
                .id(bank.getAccountNumber().toString())
                // set the timeout
                .timeout(TimeValue.timeValueSeconds(3L))
                // set the document source to index
                .source(bankDoc, XContentType.JSON);

        // index the document
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        // print the index response
        log.info("{}", indexResponse);

        // assert that the status of response whether is '201 CREATED'
        MatcherAssert.assertThat(indexResponse.status(), Matchers.equalTo(RestStatus.CREATED));

    }

    @Test
    public void testIndexWithoutId() throws IOException {

        Bank bank = new Bank();
        bank.setAccountNumber(18L);
        bank.setBalance(4180L);
        bank.setFirstName("Dale");
        bank.setLastName("Adams");
        bank.setAge(33L);
        bank.setGender("M");
        bank.setAddress("467 Hutchinson Court");
        bank.setEmployer("Boink");
        bank.setEmail("daleadams@boink.com");
        bank.setCity("Orick");
        bank.setState("MD");
        String bankDoc = JSON.toJSONString(bank);

        IndexRequest indexRequest = new IndexRequest()
                .index("bank")
                .source(bankDoc, XContentType.JSON);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        log.info("{}", indexResponse);

        MatcherAssert.assertThat(indexResponse.status(), Matchers.equalTo(RestStatus.CREATED));

    }

    @Test
    public void testExists() throws IOException {

        GetRequest getRequest = new GetRequest("bank", "13");

        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);

        log.info("exists: {}", exists);

        MatcherAssert.assertThat(exists, Matchers.is(true));

    }

    @Test
    public void testGet() throws IOException {

        GetRequest getRequest = new GetRequest("bank", "13");

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        log.info("{}", getResponse);

        Bank bank = JSON.parseObject(getResponse.getSourceAsString(), Bank.class);

        MatcherAssert.assertThat(bank.getFirstName(), Matchers.equalTo("Nanette"));

    }

    @Test
    public void testUpdate() throws IOException {

        Bank bank = new Bank();
        bank.setAddress("777 Madison Street");

        UpdateRequest updateRequest = new UpdateRequest()
                .index("bank")
                .id("13")
                .doc(JSON.toJSONString(bank), XContentType.JSON);

        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

        log.info("{}", updateResponse);

        MatcherAssert.assertThat(updateResponse.status(), Matchers.equalTo(RestStatus.OK));

    }

    @Test
    public void testDelete() throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest("bank", "13");

        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);

        log.info("{}", deleteResponse);

        MatcherAssert.assertThat(deleteResponse.status(), Matchers.equalTo(RestStatus.OK));

    }

}
