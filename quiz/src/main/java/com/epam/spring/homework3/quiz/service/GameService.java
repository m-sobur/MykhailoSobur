package com.epam.spring.homework3.quiz.service;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.dto.QuizDto;


import java.util.List;
import java.util.UUID;

public interface GameService {
    QuizDto startGame(UUID id_quiz);

    Integer checkResultOfGame(UUID id_quiz, List<AnswerVariantDto> userAnswers, UUID id_usr);
}
