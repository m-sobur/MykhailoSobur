package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.mapper.AnswerVariantMapper;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.repository.AnswerVariantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerVariantServiceImpl implements AnswerVariantService {
    private final AnswerVariantMapper answerVariantMapper;
    private final AnswerVariantRepository answerVariantRepository;

    @Override
    public AnswerVariantDto getAnswerVariantById(UUID variant_id) {
        AnswerVariant answerVariant = answerVariantRepository.getAnswerVariantById(variant_id);
        log.info("SERVICE LAYER: getAnswerVariantById method " + variant_id);
        return answerVariantMapper.answerVariantToAnswerVariantDto(answerVariant);
    }

    @Override
    public AnswerVariantDto createAnswerVariant(AnswerVariantDto answerVariantDto) {
        AnswerVariant answerVariant = answerVariantMapper.answerVariantDtoToAnswerVariant(answerVariantDto);
        answerVariant.setVariant_id(UUID.randomUUID());
        answerVariant = answerVariantRepository.createAnswerVariant(answerVariant);
        log.info("SERVICE LAYER: createAnswerVariant method " + answerVariant);
        return answerVariantMapper.answerVariantToAnswerVariantDto(answerVariant);
    }

    @Override
    public void deleteAnswerVariantById(UUID variant_id) {
        answerVariantRepository.deleteAnswerVariantById(variant_id);
        log.info("SERVICE LAYER: deleteAnswerVariantById " + variant_id);
    }

    @Override
    public List<AnswerVariantDto> getAllAnswerVariantByParentQuestionId(UUID parent_question_id) {
        List<AnswerVariant> answerVariantList = answerVariantRepository.getAllAnswerVariantByParentQuestionId(parent_question_id);
        log.info("SERVICE LAYER: getAllAnswerVariantByParentQuestionId " + parent_question_id);
        return  answerVariantMapper.answerVariantListToAnswerVariantListDto(answerVariantList);
    }
}
