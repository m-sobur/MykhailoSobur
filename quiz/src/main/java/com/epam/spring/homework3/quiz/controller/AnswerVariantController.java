package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.mapper.AnswerVariantMapper;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "AnswerVariantController description for SWAGGER documentation")
public class AnswerVariantController {
    private final AnswerVariantService answerVariantService;
    private final AnswerVariantMapper answerVariantMapper;

    @GetMapping(value = "/answer-variant/{id}")
    @ApiOperation("Get answer variant by id")
    public ResponseEntity<AnswerVariantDto> getAnswerVariantDtoByID(@PathVariable Long id) {
        log.info("CONTROLLER LAYER: getAnswerVariantByID method entry ");

        ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantMapper.answerVariantToAnswerVariantDto(answerVariantService.getAnswerVariantById(id)));

        log.info("CONTROLLER LAYER: getAnswerVariantByID method exit ");
        return result;
    }

    @PostMapping(value = "/answer-variant")
    @ApiOperation("Create answer variant")
    public ResponseEntity<AnswerVariantDto> createAnswerVariantDto(@RequestBody @Validated(OnCreate.class) AnswerVariantDto answerVariantDto) {
        log.info("CONTROLLER LAYER: createAnswerVariantDto method entry");

        ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.CREATED).body(answerVariantMapper.answerVariantToAnswerVariantDto(answerVariantService.createAnswerVariant(answerVariantDto)));

        log.info("CONTROLLER LAYER: createAnswerVariantDto method exit");
        return result;
    }

    @DeleteMapping(value = "/answer-variant/{id}")
    @ApiOperation("Delete answer variant by id")
    public ResponseEntity<String> deleteAnswerVariantDtoById(@PathVariable Long id) {
        log.info("CONTROLLER LAYER: deleteAnswerVariantById method entry");

        answerVariantService.deleteAnswerVariantById(id);

        log.info("CONTROLLER LAYER: deleteAnswerVariantById method exit");
        return ResponseEntity.status(HttpStatus.OK).body("Variant with title '" + id + "' deleted successfully");
    }

    @GetMapping(value = "/question/{parentQuestionId}/answer-variant")
    @ApiOperation("Get all answer variant by parent question id")
    public ResponseEntity<List<AnswerVariantDto>> getAllAnswerVariantDtoByQuestionId(@PathVariable Long parentQuestionId) {
        log.info("CONTROLLER LAYER: getAllAnswerVariantByQuestionId method entry");

        ResponseEntity<List<AnswerVariantDto>> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantMapper.answerVariantListToAnswerVariantListDto(answerVariantService.getAllAnswerVariantByQuestionId(parentQuestionId)));

        log.info("CONTROLLER LAYER: getAllAnswerVariantByQuestionId method exit");
        return result;
    }
}
