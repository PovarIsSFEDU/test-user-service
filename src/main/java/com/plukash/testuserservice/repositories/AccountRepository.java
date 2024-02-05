package com.plukash.testuserservice.repositories;

import com.plukash.testuserservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}