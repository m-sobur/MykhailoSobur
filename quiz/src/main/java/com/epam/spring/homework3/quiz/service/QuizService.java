package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.service.model.Quiz;

import java.util.List;

public interface QuizService {
    Quiz getQuizByTitle(String title);

    Quiz getQuizById(Long id);

    Quiz createQuiz(QuizDto quizDto);

//    Quiz updateQuizByTitle(String title, QuizDto quizDto);

    void deleteQuizByTitle(String title);

    List<Quiz> getAllQuizesByCreatorId(Long creatorId);
}
