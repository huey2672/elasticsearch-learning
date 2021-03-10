package com.huey.learning.elasticsearch.springboot.repository.sample.service;

import com.huey.learning.elasticsearch.springboot.repository.sample.entity.Bank;
import com.huey.learning.elasticsearch.springboot.repository.sample.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * @author huey
 */
@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public void saveBank(Bank bank) {
        bankRepository.save(bank);
    }

    public void saveBanks(Collection<Bank> banks) {
        bankRepository.saveAll(banks);
    }

    public Optional<Bank> getBank(Long accountNumber) {
        return bankRepository.findById(accountNumber);
    }

    public void deleteBank(Long accountNumber) {
        bankRepository.deleteById(accountNumber);
    }

}
