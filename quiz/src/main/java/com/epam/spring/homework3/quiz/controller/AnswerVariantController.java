package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.mapper.AnswerVariantMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/answerVariant")
@RequiredArgsConstructor
public class AnswerVariantController {
    private final AnswerVariantService answerVariantService;
    private final AnswerVariantMapper answerVariantMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<AnswerVariantDto> getAnswerVariantByID(@PathVariable Integer id) {
        try {
            ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantMapper.answerVariantToAnswerVariantDto(answerVariantService.getAnswerVariantById(id)));
            LOGGER.info("CONTROLLER LAYER: getAnswerVariantByID method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<AnswerVariantDto> createAnswerVariantDto(@RequestBody AnswerVariantDto answerVariantDto) {
        try {
            ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.CREATED).body(answerVariantMapper.answerVariantToAnswerVariantDto(answerVariantService.createAnswerVariant(answerVariantDto)));
            LOGGER.info("CONTROLLER LAYER: createAnswerVariantDto method ");
            return result;
        } catch (ElementAlreadyExistException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteAnswerVariantById(@PathVariable Integer id) {
        try {
            answerVariantService.deleteAnswerVariantById(id);
            LOGGER.info("CONTROLLER LAYER: deleteQuizByTitle method ");
            return ResponseEntity.status(HttpStatus.OK).body("Variant with title '" + id + "' deleted successfully");
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/getAllAnswerVariantByParentQuestionId/{parentQuestionId}")
    public ResponseEntity<List<AnswerVariantDto>> getAllAnswerVariantByParentQuestionId(@PathVariable Integer parentQuestionId) {
        try {
            ResponseEntity<List<AnswerVariantDto>> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantMapper.answerVariantListToAnswerVariantListDto(answerVariantService.getAllAnswerVariantByParentQuestionId(parentQuestionId)));
            LOGGER.info("CONTROLLER LAYER: getAllAnswerVariantByParentQuestionId method ");
            return result;
        } catch (NoSuchElementException exception) {
            LOGGER.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}