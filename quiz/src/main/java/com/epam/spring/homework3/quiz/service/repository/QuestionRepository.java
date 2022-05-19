package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository {
    Question getQuestionByID(Integer question_id);

    Question createQuestion(Question question);

    Question updateQuestionById(Integer question_id, Question question);

    void deleteQuestionById(Integer question_id);

    List<Question> getAllQuestionsByParentQuizId(Integer parent_quiz);
}
