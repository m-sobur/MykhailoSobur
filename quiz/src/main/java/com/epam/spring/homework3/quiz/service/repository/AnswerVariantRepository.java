package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerVariantRepository extends JpaRepository<AnswerVariant, Long> {

    boolean existsById(Long id);

    List<AnswerVariant> findAnswerVariantByQuestion(Question question);

}
