package com.huey.learning.elasticsearch.springboot.repository.sample.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author huey
 */
@Data
@Document(indexName = "bank")
public class Bank {

    @Id
    @Field("account_number")
    private Long accountNumber;

    @Field("balance")
    private Long balance;

    @Field("firstname")
    private String firstName;

    @Field("lastname")
    private String lastName;

    @Field("age")
    private Long age;

    @Field("gender")
    private String gender;

    @Field("address")
    private String address;

    @Field("employer")
    private String employer;

    @Field("email")
    private String email;

    @Field("city")
    private String city;

    @Field("state")
    private String state;

}
