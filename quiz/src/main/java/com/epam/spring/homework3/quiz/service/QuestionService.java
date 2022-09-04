package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.service.model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    Question getQuestionByID(Integer id);

    Question createQuestion(QuestionDto questionDto);

    Question updateQuestionById(Integer id, QuestionDto questionDto);

    void deleteQuestionById(Integer id);

    List<Question> getAllQuestionsByParentQuizId(Integer parentQuizId);
}
