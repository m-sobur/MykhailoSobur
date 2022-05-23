package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;

public interface GameService {
    QuizDto startGame(Integer id_quiz);

    String checkResultOfGame(QuizDto quizDto, Integer id_quiz, String userName);
}
