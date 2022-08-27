package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;

import java.util.List;

public interface AnswerVariantService {
    AnswerVariant getAnswerVariantById(Integer id);

    AnswerVariant createAnswerVariant(AnswerVariantDto answerVariantDto);

    void deleteAnswerVariantById(Integer id);

    List<AnswerVariant> getAllAnswerVariantByParentQuestionId(Integer parentQuestionId);
}
