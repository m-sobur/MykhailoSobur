package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.Question;

import java.util.List;

public interface QuestionRepository {
    Question getQuestionByID(Integer id);

    Question createQuestion(Question question);

    Question updateQuestionById(Integer id, Question question);

    void deleteQuestionById(Integer id);

    List<Question> getAllQuestionsByParentQuizId(Integer parentQuizId);
}