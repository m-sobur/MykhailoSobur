package com.epam.spring.homework3.quiz.service.repository;

import com.epam.spring.homework3.quiz.service.model.Quiz;

import java.util.List;
import java.util.UUID;

public interface QuizRepository {
    Quiz getQuizByTitle(String title);

    Quiz createQuiz(Quiz quiz);

    Quiz updateQuizByTitle(String title, Quiz quiz);

    void deleteQuizByTitle(String title);

    List<Quiz> getAllQuizesByCreatorId(Integer creator);

}
