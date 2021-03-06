package com.huey.learning.elasticsearch.rest.high.sample.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author huey
 */
@Data
public class Bank {

    @JSONField(name = "account_number")
    private Long accountNumber;

    @JSONField(name = "balance")
    private Long balance;

    @JSONField(name = "firstname")
    private String firstName;

    @JSONField(name = "lastname")
    private String lastName;

    @JSONField(name = "age")
    private Long age;

    @JSONField(name = "gender")
    private String gender;

    @JSONField(name = "address")
    private String address;

    @JSONField(name = "employer")
    private String employer;

    @JSONField(name = "email")
    private String email;

    @JSONField(name = "city")
    private String city;

    @JSONField(name = "state")
    private String state;

}
