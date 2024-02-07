package com.plukash.testuserservice.services;

import com.plukash.testuserservice.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void updateBalance() {
        var accounts = accountRepository.findAll();
        for (var acc : accounts) {
            var prom_val = acc.getBalance().multiply(BigDecimal.valueOf(1.1)).setScale(2, RoundingMode.UP);
            if (prom_val.compareTo(acc.getMaxBalance()) < 0) {
                accountRepository.updateBalanceById(acc.getId(), prom_val);
            }
        }
    }
}
