package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionService{
    Question getQuestionByID(Long id);

    Question createQuestion(QuestionDto questionDto);

    Question updateQuestionById(Long id, QuestionDto questionDto);

    void deleteQuestionById(Long id);

    List<Question> getAllQuestionsByParentQuizId(Long parentQuizId);
}
