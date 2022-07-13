package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final QuizMapper quizMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/get/{title}")
    public ResponseEntity<QuizDto> getQuizByTitle(@PathVariable String title) {
        try {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.OK).body(quizMapper.quizToQuizDto(quizService.getQuizByTitle(title)));
            LOGGER.info("CONTROLLER LAYER: getQuizByTitle method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/update/{title}")
    public ResponseEntity<QuizDto> updateQuizByTitle(@PathVariable String title, @RequestBody QuizDto quizDto) {
        try {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.OK).body(quizMapper.quizToQuizDto(quizService.updateQuizByTitle(title, quizDto)));
            LOGGER.info("CONTROLLER LAYER: updateQuizByTitle method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@RequestBody QuizDto quizDto) {
        try {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.CREATED).body(quizMapper.quizToQuizDto(quizService.createQuiz(quizDto)));
            LOGGER.info("CONTROLLER LAYER: createQuiz method ");
            return result;
        } catch (ElementAlreadyExistException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete/{title}")
    public ResponseEntity<String> deleteQuizByTitle(@PathVariable String title) {
        try {
            quizService.deleteQuizByTitle(title);
            LOGGER.info("CONTROLLER LAYER: deleteQuizByTitle method ");
            return ResponseEntity.status(HttpStatus.OK).body("Quiz with title '" + title + "' deleted successfully");
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/getAllByCreatorId/{creatorId}")
    public ResponseEntity<List<QuizDto>> getAllQuizesByCreatorId(@PathVariable Integer creatorId) {
        try {
            ResponseEntity<List<QuizDto>> result = ResponseEntity.status(HttpStatus.OK).body(quizMapper.quizsToQuizsDto(quizService.getAllQuizesByCreatorId(creatorId)));
            LOGGER.info("CONTROLLER LAYER: getAllQuizesByCreatorId method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}