package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.Question;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    boolean existsById(@NonNull Long id);

    List<Question> findQuestionByQuizId(Long id, Pageable pageable);

}
