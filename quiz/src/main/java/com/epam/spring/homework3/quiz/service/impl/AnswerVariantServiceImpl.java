package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.AnswerVariantDto;
import com.epam.spring.homework3.quiz.controller.mapper.AnswerVariantMapper;
import com.epam.spring.homework3.quiz.exception.repository.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.repository.AnswerVariantRepository;
import com.epam.spring.homework3.quiz.service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerVariantServiceImpl implements AnswerVariantService {
    private final AnswerVariantMapper answerVariantMapper;
    private final AnswerVariantRepository answerVariantRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public AnswerVariant getAnswerVariantById(Long id) throws NoSuchElementException {
        log.info("SERVICE LAYER: getAnswerVariantById method entry " + id);

        AnswerVariant answerVariant = answerVariantRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Answer variant with " + id + " doesn't exsist at DB "));

        log.info("SERVICE LAYER: getAnswerVariantById method exit " + id);
        return answerVariant;
    }

    @Override
    @Transactional
    public AnswerVariant createAnswerVariant(AnswerVariantDto answerVariantDto) throws ElementAlreadyExistException {
        log.info("SERVICE LAYER: createAnswerVariant method entry ");

        AnswerVariant answerVariant = answerVariantMapper.answerVariantDtoToAnswerVariant(answerVariantDto);
        Long questionId = answerVariantDto.getQuestionId();

        Question question = questionRepository
                .findById(questionId).orElseThrow(() -> new NoSuchElementException("Question not found for linking with answerVariant"));

        answerVariant.setQuestion(question);
        answerVariantRepository.save(answerVariant);

        log.info("SERVICE LAYER: createAnswerVariant method exit" + answerVariant);
        return answerVariant;
    }

    @Override
    @Transactional
    public void deleteAnswerVariantById(Long id) throws NoSuchElementException {
        log.info("SERVICE LAYER: deleteAnswerVariantById entry" + id);

        answerVariantRepository.deleteById(id);

        log.info("SERVICE LAYER: deleteAnswerVariantById exit" + id);
    }

    @Override
    @Transactional
    public List<AnswerVariant> getAllAnswerVariantByQuestionId(Long questionId) {
        log.info("SERVICE LAYER: getAllAnswerVariantByParentQuestionId entry ");

        Question question = questionRepository
                .findById(questionId)
                .orElseThrow(() -> new NoSuchElementException("question with " + questionId + "doesn't exsist at DB"));

        List<AnswerVariant> answerVariantList = answerVariantRepository.findAnswerVariantByQuestion(question);

        log.info("SERVICE LAYER: getAllAnswerVariantByParentQuestionId exit " + answerVariantList);
        return answerVariantList;
    }
}
