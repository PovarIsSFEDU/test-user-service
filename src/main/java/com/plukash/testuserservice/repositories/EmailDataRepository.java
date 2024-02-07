package com.plukash.testuserservice.repositories;

import com.plukash.testuserservice.entities.EmailData;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {
    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<EmailData> findByEmail(String email);

    long countDistinctByEmail(String email);
}