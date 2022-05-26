package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.service.model.Quiz;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface QuizService {
    Quiz getQuizByTitle(String title);

    Quiz getQuizById(Integer id_quiz);

    Quiz createQuiz(QuizDto quizDto);

    Quiz updateQuizByTitle(String title, QuizDto quizDto);

    void deleteQuizByTitle(String title);

    List<Quiz> getAllQuizesByCreatorId(Integer creator);
}
