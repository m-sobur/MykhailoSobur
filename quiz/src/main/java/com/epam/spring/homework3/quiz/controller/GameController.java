package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.service.GameService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/startGameById/{idQuiz}")
    public ResponseEntity<QuizDto> startGame(@PathVariable Integer idQuiz) {
        try {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.OK).body(gameService.startGame(idQuiz));
            LOGGER.info("CONTROLLER LAYER: startGame method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value = "/checkResult/{idQuiz}/{userName}")
    public ResponseEntity<String> checkResult(@RequestBody QuizDto quizDto, @PathVariable Integer idQuiz, @PathVariable String userName) {
        try {
            ResponseEntity<String> result = ResponseEntity.status(HttpStatus.OK).body(gameService.checkResultOfGame(quizDto, idQuiz, userName));
            LOGGER.info("CONTROLLER LAYER: checkResult method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}