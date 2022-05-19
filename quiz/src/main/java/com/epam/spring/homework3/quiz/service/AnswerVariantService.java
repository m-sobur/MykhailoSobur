package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;

import java.util.List;
import java.util.UUID;

public interface AnswerVariantService {
    AnswerVariantDto getAnswerVariantById(Integer variant_id);

    AnswerVariantDto createAnswerVariant(AnswerVariantDto answerVariantDto);

    void deleteAnswerVariantById(Integer variant_id);


    List<AnswerVariantDto> getAllAnswerVariantDtoByParentQuestionId(Integer parent_question_id);

    List<AnswerVariant> getAllAnswerVariantByParentQuestionId(Integer parent_question_id);

}
