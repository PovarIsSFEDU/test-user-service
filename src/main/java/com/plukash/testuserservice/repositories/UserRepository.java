package com.plukash.testuserservice.repositories;

import com.plukash.testuserservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u inner join u.emails emails where emails.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("select u from User u inner join u.phones phones where phones.phone = ?1")
    Optional<User> findByPhone(String phone);

    @Query("select u from User u left join u.emails emails left join u.phones phones " +
            "where emails.email = ?1 or phones.phone = ?1")
    Optional<User> findByEmailOrPhone(String email_or_phone);


}
