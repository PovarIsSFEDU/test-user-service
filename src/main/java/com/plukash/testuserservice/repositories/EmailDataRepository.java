package com.plukash.testuserservice.repositories;

import com.plukash.testuserservice.entities.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {
}