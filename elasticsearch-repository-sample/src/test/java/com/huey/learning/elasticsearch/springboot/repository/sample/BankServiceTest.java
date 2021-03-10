package com.huey.learning.elasticsearch.springboot.repository.sample;

import com.huey.learning.elasticsearch.springboot.repository.sample.entity.Bank;
import com.huey.learning.elasticsearch.springboot.repository.sample.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author huey
 */
@Slf4j
@SpringBootTest
public class BankServiceTest {

    @Autowired
    private BankService bankService;

    @Test
    void testSaveBank() {

        Bank bank = new Bank();
        bank.setAccountNumber(12L);
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

        bankService.saveBank(bank);

    }

    @Test
    void testGetBank() {

        Optional<Bank> bankOpt = bankService.getBank(12L);
        bankOpt.ifPresent(bank -> {
            log.info("{}", bank);
        });

    }

    @Test
    void testDeleteBank() {

        bankService.deleteBank(12L);

    }

}
