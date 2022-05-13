package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository {
    Question getQuestionByID(UUID question_id);

    Question createQuestion(Question question);

    Question updateQuestionById(UUID question_id, Question question);

    void deleteQuestionById(UUID question_id);

    List<Question> getAllQuestionsByParentQuizId(UUID parent_quiz);
}
