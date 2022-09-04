package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.Quiz;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    boolean existsByTitle(String title);

    Optional<Quiz> findQuizByTitle(String title);

    void deleteByTitle(String title);

    @Query(value = "SELECT new com.epam.spring.homework3.quiz.service.model.Quiz(q.id, q.title, q.creationDate, q.creatorId, q.quizType) FROM Quiz q WHERE q.creatorId = ?1")
    List<Quiz> findQuizByCreatorId(Long creatorId, Pageable pageable);

}
