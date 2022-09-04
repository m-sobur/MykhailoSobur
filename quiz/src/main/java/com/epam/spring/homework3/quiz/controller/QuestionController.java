package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.assembler.QuestionAssembler;
import com.epam.spring.homework3.quiz.controller.assembler.model.QuestionModel;
import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.quiz.controller.mapper.QuestionMapper;
import com.epam.spring.homework3.quiz.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "QuestionController description for SWAGGER documentation")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final QuestionAssembler questionAssembler;

    @GetMapping(value = "/question/{id}")
    @ApiOperation("Get question by id")
    public QuestionModel getQuestionByID(@PathVariable Integer id) {
        QuestionDto result = questionMapper.questionToQuestionDto(questionService.getQuestionByID(id));
        log.info("CONTROLLER LAYER: getQuestionByID method ");
        return questionAssembler.toModel(result);
    }

    @PutMapping(value = "/question/{id}")
    @ApiOperation("Update question by id")
    public QuestionModel updateQuestionById(@PathVariable Integer id, @RequestBody @Validated(OnUpdate.class) QuestionDto questionDto) {
        QuestionDto result = questionMapper.questionToQuestionDto(questionService.updateQuestionById(id, questionDto));
        log.info("CONTROLLER LAYER: updateQuestionById method ");
        return questionAssembler.toModel(result);
    }

    @PostMapping(value = "/question")
    @ApiOperation("Create question")
    public QuestionModel createQuestion(@RequestBody @Validated(OnCreate.class) QuestionDto questionDto) {
        QuestionDto result = questionMapper.questionToQuestionDto(questionService.createQuestion(questionDto));
        log.info("CONTROLLER LAYER: createQuestion method ");
        return questionAssembler.toModel(result);
    }

    @DeleteMapping(value = "/question/{id}")
    @ApiOperation("Delete question by id")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id) {
        questionService.deleteQuestionById(id);
        log.info("CONTROLLER LAYER: deleteQuizByTitle method ");
        return ResponseEntity.status(HttpStatus.OK).body("Question with title '" + id + "' deleted successfully");
    }

    @GetMapping(value = "/quiz/{parentQuizId}/question")
    @ApiOperation("Get all question by parent id")
    public List<QuestionModel> getAllQuestionsByParentQuizId(@PathVariable Integer parentQuizId) {
        List<QuestionDto> result = questionMapper.questionListToQuestionListDto(questionService.getAllQuestionsByParentQuizId(parentQuizId));
        List<QuestionModel> list = new ArrayList(result.size());

        for (QuestionDto questionDto : result) {
            list.add(questionAssembler.toModel(questionDto));
        }

        log.info("CONTROLLER LAYER: getAllQuestionsByParentQuizId method ");
        return list;
    }
}
