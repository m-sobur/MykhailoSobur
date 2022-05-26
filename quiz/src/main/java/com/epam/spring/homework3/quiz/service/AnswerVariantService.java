package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;

import java.util.List;
import java.util.UUID;

public interface AnswerVariantService {
    AnswerVariant getAnswerVariantById(Integer variant_id);

    AnswerVariant createAnswerVariant(AnswerVariantDto answerVariantDto);

    void deleteAnswerVariantById(Integer variant_id);

    List<AnswerVariant> getAllAnswerVariantByParentQuestionId(Integer parent_question_id);
}
