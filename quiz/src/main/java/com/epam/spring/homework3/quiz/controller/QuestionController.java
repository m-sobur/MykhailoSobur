package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuestionMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<QuestionDto> getQuestionByID(@PathVariable Integer id) {
        try {
            ResponseEntity<QuestionDto> result = ResponseEntity.status(HttpStatus.OK).body(questionMapper.questionToQuestionDto(questionService.getQuestionByID(id)));
            LOGGER.info("CONTROLLER LAYER: getQuestionByID method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/update/{questioId}")
    public ResponseEntity<QuestionDto> updateQuestionById(@PathVariable Integer questioId, @RequestBody QuestionDto questionDto) {
        try {
            ResponseEntity<QuestionDto> result = ResponseEntity.status(HttpStatus.OK).body(questionMapper.questionToQuestionDto(questionService.updateQuestionById(questioId, questionDto)));
            LOGGER.info("CONTROLLER LAYER: updateQuestionById method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        try {
            ResponseEntity<QuestionDto> result = ResponseEntity.status(HttpStatus.CREATED).body(questionMapper.questionToQuestionDto(questionService.createQuestion(questionDto)));
            LOGGER.info("CONTROLLER LAYER: createQuestion method ");
            return result;
        } catch (ElementAlreadyExistException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id) {
        try {
            questionService.deleteQuestionById(id);
            LOGGER.info("CONTROLLER LAYER: deleteQuizByTitle method ");
            return ResponseEntity.status(HttpStatus.OK).body("Question with title '" + id + "' deleted successfully");
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/getAllQuestionsByParentQuizId/{parentQuizId}")
    public ResponseEntity<List<QuestionDto>> getAllQuestionsByParentQuizId(@PathVariable Integer parentQuizId) {
        try {
            ResponseEntity<List<QuestionDto>> result = ResponseEntity.status(HttpStatus.OK).body(questionMapper.questionListToQuestionListDto(questionService.getAllQuestionsByParentQuizId(parentQuizId)));
            LOGGER.info("CONTROLLER LAYER: getAllQuestionsByParentQuizId method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
