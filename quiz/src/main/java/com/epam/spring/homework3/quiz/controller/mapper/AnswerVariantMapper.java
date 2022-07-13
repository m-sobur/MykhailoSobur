package com.epam.spring.homework3.quiz.controller.mapper;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerVariantMapper {

    @Mapping(target = "value", ignore = true)
    AnswerVariantDto answerVariantToAnswerVariantDto(AnswerVariant answerVariant);

    AnswerVariant answerVariantDtoToAnswerVariant(AnswerVariantDto answerVariantDto);

    @Mapping(target = "value", ignore = true)
    List<AnswerVariantDto> answerVariantListToAnswerVariantListDto(List<AnswerVariant> answerVariantList);

    List<AnswerVariant> answerVariantListDtoToAnswerVariantList(List<AnswerVariantDto> answerVariantListDto);
}