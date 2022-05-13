package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;

import java.util.List;
import java.util.UUID;

public interface AnswerVariantService {
    AnswerVariantDto getAnswerVariantById(UUID variant_id);

    AnswerVariantDto createAnswerVariant(AnswerVariantDto answerVariantDto);

    void deleteAnswerVariantById(UUID variant_id);

    List<AnswerVariantDto> getAllAnswerVariantByParentQuestionId(UUID parent_question_id);
}
