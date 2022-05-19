package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.service.model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    QuestionDto getQuestionByID(Integer question_id);

    QuestionDto createQuestion(QuestionDto questionDto);

    QuestionDto updateQuestionById(Integer question_id, QuestionDto questionDto);

    void deleteQuestionById(Integer question_id);

    List<QuestionDto> getAllQuestionsDtoByParentQuizId(Integer parent_quiz);

    List<Question> getAllQuestionsByParentQuizId(Integer parent_quiz);

}
