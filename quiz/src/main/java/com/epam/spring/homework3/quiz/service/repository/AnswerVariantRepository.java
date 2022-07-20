package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.AnswerVariant;

import java.util.List;

public interface AnswerVariantRepository {
    AnswerVariant getAnswerVariantById(Integer id);

    AnswerVariant createAnswerVariant(AnswerVariant answerVariant);

    void deleteAnswerVariantById(Integer id);

    List<AnswerVariant> getAllAnswerVariantByParentQuestionId(Integer parentQuestionId);
}
