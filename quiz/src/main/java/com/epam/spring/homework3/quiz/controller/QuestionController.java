package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuestionMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@Api(tags = "QuestionController description for SWAGGER documentation")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping(value = "/get/{id}")
    @ApiOperation("Get question by id")
    public ResponseEntity<QuestionDto> getQuestionByID(@PathVariable Integer id) {
        try {
            ResponseEntity<QuestionDto> result = ResponseEntity.status(HttpStatus.OK).body(questionMapper.questionToQuestionDto(questionService.getQuestionByID(id)));
            log.info("CONTROLLER LAYER: getQuestionByID method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(value = "/update/{id}")
    @ApiOperation("Update question by id")
    public ResponseEntity<QuestionDto> updateQuestionById(@PathVariable Integer id, @RequestBody QuestionDto questionDto) {
        try {
            ResponseEntity<QuestionDto> result = ResponseEntity.status(HttpStatus.OK).body(questionMapper.questionToQuestionDto(questionService.updateQuestionById(id, questionDto)));
            log.info("CONTROLLER LAYER: updateQuestionById method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @ApiOperation("Create question")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        try {
            ResponseEntity<QuestionDto> result = ResponseEntity.status(HttpStatus.CREATED).body(questionMapper.questionToQuestionDto(questionService.createQuestion(questionDto)));
            log.info("CONTROLLER LAYER: createQuestion method ");
            return result;
        } catch (ElementAlreadyExistException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation("Delete question by id")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id) {
        try {
            questionService.deleteQuestionById(id);
            log.info("CONTROLLER LAYER: deleteQuizByTitle method ");
            return ResponseEntity.status(HttpStatus.OK).body("Question with title '" + id + "' deleted successfully");
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/getAllQuestionsByParentQuizId/{parentQuizId}")
    @ApiOperation("Get all question by parent id")
    public ResponseEntity<List<QuestionDto>> getAllQuestionsByParentQuizId(@PathVariable Integer parentQuizId) {
        try {
            ResponseEntity<List<QuestionDto>> result = ResponseEntity.status(HttpStatus.OK).body(questionMapper.questionListToQuestionListDto(questionService.getAllQuestionsByParentQuizId(parentQuizId)));
            log.info("CONTROLLER LAYER: getAllQuestionsByParentQuizId method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}