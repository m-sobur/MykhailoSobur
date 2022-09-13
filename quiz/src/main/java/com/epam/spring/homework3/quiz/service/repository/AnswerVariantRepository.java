package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.model.Question;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerVariantRepository extends JpaRepository<AnswerVariant, Long> {

    boolean existsById(@NonNull Long id);

    List<AnswerVariant> findAnswerVariantByQuestion(Question question);

}
