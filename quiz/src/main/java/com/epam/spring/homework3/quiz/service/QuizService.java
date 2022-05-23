package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface QuizService {
    QuizDto getQuizByTitle(String title);

    QuizDto getQuizById(Integer id_quiz);

    QuizDto createQuiz(QuizDto quizDto);

    QuizDto updateQuizByTitle(String title, QuizDto quizDto);

    void deleteQuizByTitle(String title);

    List<QuizDto> getAllQuizesByCreatorId(Integer creator);
}
