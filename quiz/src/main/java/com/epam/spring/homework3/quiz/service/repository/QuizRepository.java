package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    boolean existsById(Long id);

    Optional<Quiz> findQuizByTitle(String title);

    void deleteByTitle(String title);

}
