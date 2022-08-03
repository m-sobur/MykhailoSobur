package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;

public interface GameService {
    QuizDto startGame(Long id);

    String checkResultOfGame(QuizDto quizDto, Long id, String userName);
}
