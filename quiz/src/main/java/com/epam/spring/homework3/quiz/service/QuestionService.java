package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.service.model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    QuestionDto getQuestionByID(UUID question_id);

    QuestionDto createQuestion(QuestionDto questionDto);

    QuestionDto updateQuestionById(UUID question_id, QuestionDto questionDto);

    void deleteQuestionById(UUID question_id);

    List<QuestionDto> getAllQuestionsByParentQuizId(UUID parent_quiz);
}
