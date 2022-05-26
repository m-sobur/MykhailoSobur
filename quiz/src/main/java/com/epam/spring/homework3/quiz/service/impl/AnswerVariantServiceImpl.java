package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.mapper.AnswerVariantMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.repository.AnswerVariantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerVariantServiceImpl implements AnswerVariantService {
    private final AnswerVariantMapper answerVariantMapper;
    private final AnswerVariantRepository answerVariantRepository;

    @Override
    public AnswerVariant getAnswerVariantById(Integer variant_id) throws NoSuchElementException {
        log.info("SERVICE LAYER: getAnswerVariantById method " + variant_id);
        return answerVariantRepository.getAnswerVariantById(variant_id);
    }

    @Override
    public AnswerVariant createAnswerVariant(AnswerVariantDto answerVariantDto) throws ElementAlreadyExistException {
        AnswerVariant answerVariant = answerVariantMapper.answerVariantDtoToAnswerVariant(answerVariantDto);
        answerVariant = answerVariantRepository.createAnswerVariant(answerVariant);
        log.info("SERVICE LAYER: createAnswerVariant method " + answerVariant);
        return answerVariant;
    }

    @Override
    public void deleteAnswerVariantById(Integer variant_id) throws NoSuchElementException{
        answerVariantRepository.deleteAnswerVariantById(variant_id);
        log.info("SERVICE LAYER: deleteAnswerVariantById " + variant_id);
    }

    @Override
    public List<AnswerVariant> getAllAnswerVariantByParentQuestionId(Integer parent_question_id){
        List<AnswerVariant> answerVariantList = answerVariantRepository.getAllAnswerVariantByParentQuestionId(parent_question_id);
        log.info("SERVICE LAYER: getAllAnswerVariantByParentQuestionId " + answerVariantList);
        return  answerVariantList;
    }
}
