package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
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
@RequestMapping("/answerVariant")
@RequiredArgsConstructor
public class AnswerVariantController {
    private final AnswerVariantService answerVariantService;

    @GetMapping(value = "/get/{variant_id}")
    public ResponseEntity<AnswerVariantDto> getAnswerVariantByID(@PathVariable UUID variant_id) {
        try {
            ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantService.getAnswerVariantById(variant_id));
            log.info("CONTROLLER LAYER: getAnswerVariantByID method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping
    public ResponseEntity<AnswerVariantDto> createAnswerVariantDto(@RequestBody AnswerVariantDto answerVariantDto) {
        try {
            ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.CREATED).body(answerVariantService.createAnswerVariant(answerVariantDto));
            log.info("CONTROLLER LAYER: createAnswerVariantDto method ");
            return result;
        } catch (ElementAlreadyExistException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/delete/{variant_id}")
    public ResponseEntity<String> deleteAnswerVariantById(@PathVariable UUID variant_id) {
        try {
            answerVariantService.deleteAnswerVariantById(variant_id);
            log.info("CONTROLLER LAYER: deleteQuizByTitle method ");
            return ResponseEntity.status(HttpStatus.OK).body("Variant with title '" + variant_id + "' deleted successfully");
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping(value = "/getAllAnswerVariantByParentQuestionId/{parent_question_id}")
    public ResponseEntity<List<AnswerVariantDto>> getAllAnswerVariantByParentQuestionId(@PathVariable UUID parent_question_id) {
        try {
            ResponseEntity<List<AnswerVariantDto>> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantService.getAllAnswerVariantByParentQuestionId(parent_question_id));
            log.info("CONTROLLER LAYER: getAllAnswerVariantByParentQuestionId method ");
            return result;
        } catch (NoSuchElementException exception) {
            log.warn(exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
