package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.mapper.AnswerVariantMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.repository.AnswerVariantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AnswerVariantServiceImpl implements AnswerVariantService {
    private final AnswerVariantMapper answerVariantMapper;
    private final AnswerVariantRepository answerVariantRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public AnswerVariant getAnswerVariantById(Integer id) throws NoSuchElementException {
        LOGGER.info("SERVICE LAYER: getAnswerVariantById method " + id);
        return answerVariantRepository.getAnswerVariantById(id);
    }

    @Override
    public AnswerVariant createAnswerVariant(AnswerVariantDto answerVariantDto) throws ElementAlreadyExistException {
        AnswerVariant answerVariant = answerVariantMapper.answerVariantDtoToAnswerVariant(answerVariantDto);
        answerVariant = answerVariantRepository.createAnswerVariant(answerVariant);
        LOGGER.info("SERVICE LAYER: createAnswerVariant method " + answerVariant);
        return answerVariant;
    }

    @Override
    public void deleteAnswerVariantById(Integer id) throws NoSuchElementException {
        answerVariantRepository.deleteAnswerVariantById(id);
        LOGGER.info("SERVICE LAYER: deleteAnswerVariantById " + id);
    }

    @Override
    public List<AnswerVariant> getAllAnswerVariantByParentQuestionId(Integer parentQuestionId) {
        List<AnswerVariant> answerVariantList = answerVariantRepository.getAllAnswerVariantByParentQuestionId(parentQuestionId);
        LOGGER.info("SERVICE LAYER: getAllAnswerVariantByParentQuestionId " + answerVariantList);
        return answerVariantList;
    }
}