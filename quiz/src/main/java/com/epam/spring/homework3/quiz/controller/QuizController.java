package com.epam.spring.homework3.quiz.controller;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Api(tags = "QuizController description for SWAGGER documentation")
public class QuizController {
    private final QuizService quizService;
    private final QuizMapper quizMapper;

    @GetMapping(value = "/get/{title}")
    @ApiOperation("Get quiz by title")
    public ResponseEntity<QuizDto> getQuizByTitle(@PathVariable String title) {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.OK).body(quizMapper.quizToQuizDto(quizService.getQuizByTitle(title)));
            log.info("CONTROLLER LAYER: getQuizByTitle method ");
            return result;
    }

    @PutMapping(value = "/update/{title}")
    @ApiOperation("Update quiz by title")
    public ResponseEntity<QuizDto> updateQuizByTitle(@PathVariable String title, @RequestBody @Validated(OnUpdate.class) QuizDto quizDto) {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.OK).body(quizMapper.quizToQuizDto(quizService.updateQuizByTitle(title, quizDto)));
            log.info("CONTROLLER LAYER: updateQuizByTitle method ");
            return result;
    }

    @PostMapping
    @ApiOperation("Create quiz")
    public ResponseEntity<QuizDto> createQuiz(@RequestBody @Validated(OnCreate.class) QuizDto quizDto) {
            ResponseEntity<QuizDto> result = ResponseEntity.status(HttpStatus.CREATED).body(quizMapper.quizToQuizDto(quizService.createQuiz(quizDto)));
            log.info("CONTROLLER LAYER: createQuiz method ");
            return result;
    }

    @DeleteMapping(value = "/delete/{title}")
    @ApiOperation("Delete quiz by title")
    public ResponseEntity<String> deleteQuizByTitle(@PathVariable String title) {
            quizService.deleteQuizByTitle(title);
            log.info("CONTROLLER LAYER: deleteQuizByTitle method ");
            return ResponseEntity.status(HttpStatus.OK).body("Quiz with title '" + title + "' deleted successfully");
    }

    @GetMapping(value = "/getAllByCreatorId/{creator}")
    @ApiOperation("Get all quiz's by creator id")
    public ResponseEntity<List<QuizDto>> getAllQuizesByCreatorId(@PathVariable Integer creator) {
            ResponseEntity<List<QuizDto>> result = ResponseEntity.status(HttpStatus.OK).body(quizMapper.quizsToQuizsDto(quizService.getAllQuizesByCreatorId(creator)));
            log.info("CONTROLLER LAYER: getAllQuizesByCreatorId method ");
            return result;
    }
}
