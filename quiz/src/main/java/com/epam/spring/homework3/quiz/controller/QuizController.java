package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final QuizMapper quizMapper;
    @GetMapping(value = "/get/{title}")
    public ResponseEntity<QuizDto> getQuizByTitle(@PathVariable String title) {
        try {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.OK).body(quizMapper.quizToQuizDto(quizService.getQuizByTitle(title)));
            log.info("CONTROLLER LAYER: getQuizByTitle method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/update/{title}")
    public ResponseEntity<QuizDto> updateQuizByTitle(@PathVariable String title, @RequestBody QuizDto quizDto) {
        try {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.OK).body(quizMapper.quizToQuizDto(quizService.updateQuizByTitle(title, quizDto)));
            log.info("CONTROLLER LAYER: updateQuizByTitle method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@RequestBody QuizDto quizDto) {
        try {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.CREATED).body(quizMapper.quizToQuizDto(quizService.createQuiz(quizDto)));
            log.info("CONTROLLER LAYER: createQuiz method ");
            return result;
        } catch (ElementAlreadyExistException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete/{title}")
    public ResponseEntity<String> deleteQuizByTitle(@PathVariable String title) {
        try {
            quizService.deleteQuizByTitle(title);
            log.info("CONTROLLER LAYER: deleteQuizByTitle method ");
            return ResponseEntity.status(HttpStatus.OK).body("Quiz with title '" + title + "' deleted successfully");
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/getAllByCreatorId/{creator}")
    public ResponseEntity<List<QuizDto>> getAllQuizesByCreatorId(@PathVariable Integer creator) {
        try {
            ResponseEntity<List<QuizDto>> result = ResponseEntity.status(HttpStatus.OK).body(quizMapper.quizsToQuizsDto(quizService.getAllQuizesByCreatorId(creator)));
            log.info("CONTROLLER LAYER: getAllQuizesByCreatorId method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
