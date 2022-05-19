package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.dto.QuizDto;


import java.util.List;
import java.util.UUID;

public interface GameService {
    QuizDto startGame(Integer id_quiz);

    Integer checkResultOfGame(Integer id_quiz, List<AnswerVariantDto> userAnswers, Integer id_usr);
}
