package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteUserByEmail(String email);

    @Query(value = "SELECT first_name || ' ' || last_name FROM users",
            nativeQuery = true)
    Optional<List<String>> getAllUserFirstNameAndLastName();

}
