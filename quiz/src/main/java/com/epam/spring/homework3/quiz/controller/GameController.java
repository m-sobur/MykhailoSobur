package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
@Api(tags = "GameController description for SWAGGER documentation")
public class GameController {
    private final GameService gameService;

    @GetMapping(value = "/startGameById/{id}")
    @ApiOperation("Get(start) quiz-game by id")
    public ResponseEntity<QuizDto> startGame(@PathVariable Integer id) {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.OK).body(gameService.startGame(id));
            log.info("CONTROLLER LAYER: startGame method ");
            return result;
    }

    @PostMapping(value = "/checkResult/{id}/{userName}")
    @ApiOperation("Check result by id_quiz and userName")
    public ResponseEntity<String> checkResult(@RequestBody QuizDto quizDto, @PathVariable Integer id, @PathVariable String userName) {
            ResponseEntity<String> result = ResponseEntity.status(HttpStatus.OK).body(gameService.checkResultOfGame(quizDto, id, userName));
            log.info("CONTROLLER LAYER: checkResult method ");
            return result;
    }
}
