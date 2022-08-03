package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.mapper.AnswerVariantMapper;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/answerVariant")
@RequiredArgsConstructor
@Api(tags = "AnswerVariantController description for SWAGGER documentation")
public class AnswerVariantController {
    private final AnswerVariantService answerVariantService;
    private final AnswerVariantMapper answerVariantMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/get/{id}")
    @ApiOperation("Get answer variant by id")
    public ResponseEntity<AnswerVariantDto> getAnswerVariantDtoByID(@PathVariable Long id) {
        logger.info("CONTROLLER LAYER: getAnswerVariantByID method entry ");
        ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantMapper.answerVariantToAnswerVariantDto(answerVariantService.getAnswerVariantById(id)));
        logger.info("CONTROLLER LAYER: getAnswerVariantByID method exit ");
        return result;
    }

    @PostMapping
    @ApiOperation("Create answer variant")
    public ResponseEntity<AnswerVariantDto> createAnswerVariantDto(@RequestBody @Validated(OnCreate.class) AnswerVariantDto answerVariantDto) {
        logger.info("CONTROLLER LAYER: createAnswerVariantDto method entry");
        ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.CREATED).body(answerVariantMapper.answerVariantToAnswerVariantDto(answerVariantService.createAnswerVariant(answerVariantDto)));
        logger.info("CONTROLLER LAYER: createAnswerVariantDto method exit");
        return result;
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation("Delete answer variant by id")
    public ResponseEntity<String> deleteAnswerVariantDtoById(@PathVariable Long id) {
        logger.info("CONTROLLER LAYER: deleteAnswerVariantById method entry");
        answerVariantService.deleteAnswerVariantById(id);
        logger.info("CONTROLLER LAYER: deleteAnswerVariantById method exit");
        return ResponseEntity.status(HttpStatus.OK).body("Variant with title '" + id + "' deleted successfully");
    }

    @GetMapping(value = "/getAllAnswerVariantDtoByQuestionId/{QuestionId}")
    @ApiOperation("Get all answer variant by parent question id")
    public ResponseEntity<List<AnswerVariantDto>> getAllAnswerVariantDtoByQuestionId(@PathVariable Long QuestionId) {
        logger.info("CONTROLLER LAYER: getAllAnswerVariantByQuestionId method entry");
        ResponseEntity<List<AnswerVariantDto>> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantMapper.answerVariantListToAnswerVariantListDto(answerVariantService.getAllAnswerVariantByQuestionId(QuestionId)));
        logger.info("CONTROLLER LAYER: getAllAnswerVariantByQuestionId method exit");
        return result;
    }
}
