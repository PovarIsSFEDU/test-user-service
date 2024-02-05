package com.plukash.testuserservice.repositories;

import com.plukash.testuserservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Modifying
    @Query("update Account a set a.balance = ?2 where a.id = ?1")
    void updateBalanceById(Long id, BigDecimal balance);
}