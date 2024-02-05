package com.plukash.testuserservice.repositories;

import com.plukash.testuserservice.entities.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
}