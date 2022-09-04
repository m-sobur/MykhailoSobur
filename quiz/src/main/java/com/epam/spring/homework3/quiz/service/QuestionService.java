package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.service.model.Question;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService{

    Question getQuestionById(Long id);

    Question createQuestion(QuestionDto questionDto);

    Question updateQuestionById(Long id, QuestionDto questionDto);

    void deleteQuestionById(Long id);

    List<Question> getAllQuestionsByParentQuizId(Long parentQuizId, Pageable pageable);

}
