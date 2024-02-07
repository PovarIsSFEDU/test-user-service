package com.plukash.testuserservice.repositories;

import com.plukash.testuserservice.entities.PhoneData;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<PhoneData> findByPhone(String phone);


    long countDistinctByPhone(String phone);
}