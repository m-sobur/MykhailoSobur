package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.mapper.AnswerVariantMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
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
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/answerVariant")
@RequiredArgsConstructor
@Api(tags = "AnswerVariantController description for SWAGGER documentation")
public class AnswerVariantController {
    private final AnswerVariantService answerVariantService;
    private final AnswerVariantMapper answerVariantMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/get/{id}")
    @ApiOperation("Get answer variant by id")
    public ResponseEntity<AnswerVariantDto> getAnswerVariantByID(@PathVariable Integer id) {
            ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantMapper.answerVariantToAnswerVariantDto(answerVariantService.getAnswerVariantById(id)));
            LOGGER.info("CONTROLLER LAYER: getAnswerVariantByID method ");
            return result;
    }

    @PostMapping
    @ApiOperation("Create answer variant")
    public ResponseEntity<AnswerVariantDto> createAnswerVariantDto(@RequestBody @Validated(OnCreate.class) AnswerVariantDto answerVariantDto) {
            ResponseEntity<AnswerVariantDto> result = ResponseEntity.status(HttpStatus.CREATED).body(answerVariantMapper.answerVariantToAnswerVariantDto(answerVariantService.createAnswerVariant(answerVariantDto)));
            LOGGER.info("CONTROLLER LAYER: createAnswerVariantDto method ");
            return result;
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation("Delete answer variant by id")
    public ResponseEntity<String> deleteAnswerVariantById(@PathVariable Integer id) {
            answerVariantService.deleteAnswerVariantById(id);
            LOGGER.info("CONTROLLER LAYER: deleteQuizByTitle method ");
            return ResponseEntity.status(HttpStatus.OK).body("Variant with title '" + id + "' deleted successfully");
    }

    @GetMapping(value = "/getAllAnswerVariantByParentQuestionId/{parentQuestionId}")
    @ApiOperation("Get all answer variant by parent question id")
    public ResponseEntity<List<AnswerVariantDto>> getAllAnswerVariantByParentQuestionId(@PathVariable Integer parentQuestionId) {
            ResponseEntity<List<AnswerVariantDto>> result = ResponseEntity.status(HttpStatus.OK).body(answerVariantMapper.answerVariantListToAnswerVariantListDto(answerVariantService.getAllAnswerVariantByParentQuestionId(parentQuestionId)));
            LOGGER.info("CONTROLLER LAYER: getAllAnswerVariantByParentQuestionId method ");
            return result;
    }
}
