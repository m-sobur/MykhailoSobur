package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    boolean existsByTitle(String title);

    Optional<Quiz> findQuizByTitle(String title);

    void deleteByTitle(String title);

    List<Quiz> findQuizByCreatorId(Long creatorId);

}
