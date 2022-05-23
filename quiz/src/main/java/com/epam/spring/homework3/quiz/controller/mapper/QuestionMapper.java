package com.epam.spring.homework3.quiz.controller.mapper;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.service.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto questionToQuestionDto(Question question);

    Question questionDtoToQuestion(QuestionDto questionDto);

    List<QuestionDto> questionListToQuestionListDto(List<Question> questionList);

}
