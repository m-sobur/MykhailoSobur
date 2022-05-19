package com.epam.spring.homework3.quiz.controller.mapper;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    QuizDto quizToQuizDto(Quiz quiz);

    Quiz quizDtoToQuiz(QuizDto quizDto);

    List<QuizDto> quizsToQuizsDto(List<Quiz> orders);

}
