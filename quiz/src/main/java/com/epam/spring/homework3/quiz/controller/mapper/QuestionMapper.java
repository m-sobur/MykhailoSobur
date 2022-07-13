package com.epam.spring.homework3.quiz.controller.mapper;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.service.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {AnswerVariantMapper.class})
public interface QuestionMapper {

    @Mapping(source = "question.answerVariantList", target = "answerVariantList")
    QuestionDto questionToQuestionDto(Question question);

    Question questionDtoToQuestion(QuestionDto questionDto);

    List<QuestionDto> questionListToQuestionListDto(List<Question> questionList);

    List<Question> questionListDtoToQuestionList(List<QuestionDto> questionListDto);
}