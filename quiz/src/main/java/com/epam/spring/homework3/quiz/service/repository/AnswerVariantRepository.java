package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.model.Question;

import java.util.List;
import java.util.UUID;

public interface AnswerVariantRepository {
    AnswerVariant getAnswerVariantById(UUID variant_id);

    AnswerVariant createAnswerVariant(AnswerVariant answerVariant);

    void deleteAnswerVariantById(UUID variant_id);

    List<AnswerVariant> getAllAnswerVariantByParentQuestionId(UUID parent_question_id);
}
