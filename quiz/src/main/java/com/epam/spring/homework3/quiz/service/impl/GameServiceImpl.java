package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.service.GameService;
import com.epam.spring.homework3.quiz.service.QuizService;

import java.util.List;

public class GameServiceImpl implements GameService {
    QuizService quizService;

    @Override
    public QuizDto startGame(Integer id_quiz) {
        return null;
//        return quizService.getQuizByTitle();
    }

    @Override
    public Integer checkResultOfGame(Integer id_quiz, List<AnswerVariantDto> userAnswers, Integer id_usr) {
        return null;
    }
}
