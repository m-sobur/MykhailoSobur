package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;

public interface GameService {
    QuizDto startGame(Integer id);

    String checkResultOfGame(QuizDto quizDto, Integer id, String userName);
}
