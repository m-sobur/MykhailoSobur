package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.assembler.QuizAssembler;
import com.epam.spring.homework3.quiz.controller.assembler.model.QuizModel;
import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.service.QuizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "QuizController description for SWAGGER documentation")
public class QuizController {
    private final QuizService quizService;
    private final QuizMapper quizMapper;
    private final QuizAssembler quizAssembler;

    @GetMapping(value = "/quiz/{title}")
    @ApiOperation("Get quiz by title")
    public QuizModel getQuizByTitle(@PathVariable String title) {
        log.info("CONTROLLER LAYER: getQuizByTitle method entry ");

        QuizDto result = quizMapper.quizToQuizDto(quizService.getQuizByTitle(title));

        log.info("CONTROLLER LAYER: getQuizByTitle method exit ");
        return quizAssembler.toModel(result);
    }

    @PutMapping(value = "/quiz/{title}")
    @ApiOperation("Update quiz by title")
    public QuizModel updateQuizByTitle(@PathVariable String title, @RequestBody @Validated(OnUpdate.class) QuizDto quizDto) {
        log.info("CONTROLLER LAYER: updateQuizByTitle method entry ");

        QuizDto result = quizMapper.quizToQuizDto(quizService.updateQuizByTitle(title, quizDto));

        log.info("CONTROLLER LAYER: updateQuizByTitle method exit ");
        return quizAssembler.toModel(result);
    }

    @PostMapping(value = "/quiz")
    @ApiOperation("Create quiz")
    public QuizModel createQuiz(@RequestBody @Validated(OnCreate.class) QuizDto quizDto) {
        log.info("CONTROLLER LAYER: createQuiz method entry ");

        QuizDto result = quizMapper.quizToQuizDto(quizService.createQuiz(quizDto));

        log.info("CONTROLLER LAYER: createQuiz method exit ");
        return quizAssembler.toModel(result);
    }

    @DeleteMapping(value = "/quiz/{title}")
    @ApiOperation("Delete quiz by title")
    public ResponseEntity<String> deleteQuizByTitle(@PathVariable String title) {
        log.info("CONTROLLER LAYER: deleteQuizByTitle method entry ");

        quizService.deleteQuizByTitle(title);

        log.info("CONTROLLER LAYER: deleteQuizByTitle method exit ");
        return ResponseEntity.status(HttpStatus.OK).body("Quiz with title '" + title + "' deleted successfully");
    }

    @GetMapping(value = "/user/{creator}/quiz")
    @ApiOperation("Get all quiz's by creator id")
    public List<QuizModel> getAllQuizesByCreatorId(@PathVariable Long creator, Pageable pageable) {
        log.info("CONTROLLER LAYER: getAllQuizesByCreatorId method entry ");

        List<QuizDto> result = quizMapper.quizsToQuizsDto(quizService.getAllQuizByCreatorId(creator, pageable));
        List<QuizModel> list = new ArrayList<>(result.size());

        for (QuizDto quizDto : result) {
            list.add(quizAssembler.toModel(quizDto));
        }

        log.info("CONTROLLER LAYER: getAllQuizesByCreatorId method exit");
        return list;
    }
}
