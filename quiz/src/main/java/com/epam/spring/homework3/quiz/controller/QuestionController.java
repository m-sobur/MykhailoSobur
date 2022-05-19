package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping(value = "/get/{question_id}")
    public ResponseEntity<QuestionDto> getQuestionByID(@PathVariable Integer question_id) {
        try {
            ResponseEntity<QuestionDto> result = ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionByID(question_id));
            log.info("CONTROLLER LAYER: getQuestionByID method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/update/{question_id}")
    public ResponseEntity<QuestionDto> updateQuestionById(@PathVariable Integer question_id, @RequestBody QuestionDto questionDto) {
        try {
            ResponseEntity<QuestionDto> result = ResponseEntity.status(HttpStatus.OK).body(questionService.updateQuestionById(question_id, questionDto));
            log.info("CONTROLLER LAYER: updateQuestionById method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        try {
            ResponseEntity<QuestionDto> result = ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(questionDto));
            log.info("CONTROLLER LAYER: createQuestion method ");
            return result;
        } catch (ElementAlreadyExistException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete/{question_id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer question_id) {
        try {
            questionService.deleteQuestionById(question_id);
            log.info("CONTROLLER LAYER: deleteQuizByTitle method ");
            return ResponseEntity.status(HttpStatus.OK).body("Question with title '" + question_id + "' deleted successfully");
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/getAllQuestionsByParentQuizId/{parent_quiz}")
    public ResponseEntity<List<QuestionDto>> getAllQuestionsByParentQuizId(@PathVariable Integer parent_quiz) {
        try {
            ResponseEntity<List<QuestionDto>> result = ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestionsDtoByParentQuizId(parent_quiz));
            log.info("CONTROLLER LAYER: getAllQuestionsByParentQuizId method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
