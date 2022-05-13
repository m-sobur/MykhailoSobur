package com.epam.spring.homework3.quiz.controller.mapper;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerVariantMapper {

    AnswerVariantDto answerVariantToAnswerVariantDto(AnswerVariant answerVariant);

    @Mapping(target = "variant_id", ignore = true)
    AnswerVariant answerVariantDtoToAnswerVariant(AnswerVariantDto answerVariantDto);

    List<AnswerVariantDto> answerVariantListToAnswerVariantListDto(List<AnswerVariant> answerVariantList);

    @Mapping(target = "variant_id", ignore = true)
    List<AnswerVariant> answerVariantListDtoToAnswerVariantList(List<AnswerVariantDto> answerVariantDtoList);
}
