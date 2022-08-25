package com.epam.spring.homework3.quiz.controller.mapper;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {QuestionMapper.class})
public interface QuizMapper {

    @Mapping(source = "quiz.questionList", target = "questionList")
    QuizDto quizToQuizDto(Quiz quiz);

    @Mapping(target = "creationDate", ignore = true)
    Quiz quizDtoToQuiz(QuizDto quizDto);

    @Mapping(source = "quiz.questionList", target = "questionList")
    List<QuizDto> quizsToQuizsDto(List<Quiz> orders);

}
