package com.epam.spring.homework3.quiz.service.repository.impl;

import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.repository.AnswerVariantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class AnswerVariantRepositoryImpl implements AnswerVariantRepository {
    private final List<AnswerVariant> temporaryDataBase = new ArrayList<>();

    @Override
    public AnswerVariant getAnswerVariantById(Integer variant_id) throws NoSuchElementException {
        AnswerVariant resultAnswerVariant = temporaryDataBase.stream()
                .filter(answerVariant -> answerVariant.getVariant_id().equals(variant_id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("AnswerVariant not found in the 'temporararyDataBase' while executing getAnswerVariantById"));
        log.info("REPOSITORY LAYER: getAnswerVariantById method ");
        return resultAnswerVariant;
    }

    @Override
    public AnswerVariant createAnswerVariant(AnswerVariant answerVariant) throws ElementAlreadyExistException {
        temporaryDataBase.add(answerVariant);
        log.info("REPOSITORY LAYER: createAnswerVariant method ");
        return answerVariant;
    }

    @Override
    public void deleteAnswerVariantById(Integer variant_id) throws NoSuchElementException {
        boolean isDeleted = temporaryDataBase.removeIf(answerVariant -> answerVariant.getVariant_id().equals(variant_id));
        if (!isDeleted) {
            throw new NoSuchElementException("Answer with " + variant_id + " not found in the 'temporararyDataBase' while executing deleteAnswerVariantById");
        }
        log.info("REPOSITORY LAYER: deleteAnswerVariantById method ");
    }

    @Override
    public List<AnswerVariant> getAllAnswerVariantByParentQuestionId(Integer parent_question_id) {
//        boolean isEmpty = temporaryDataBase.isEmpty();
//        if (isEmpty) {
//            throw new NoSuchElementException("Question with " + parent_question_id + " id have not any answers  in the 'temporararyDataBase' while executing getAllAnswerVariantByParentQuestionId");
//        } else {
//            log.info("REPOSITORY LAYER: getAllAnswerVariantByParentQuestionId method ");
//            return temporaryDataBase.stream()
//                    .filter(answerVariant -> answerVariant.getParent_question_id().equals(parent_question_id))
//                    .collect(Collectors.toList());
//        }
        log.info("REPOSITORY LAYER: getAllAnswerVariantByParentQuestionId method ");
        return temporaryDataBase.stream()
                .filter(answerVariant -> answerVariant.getParent_question_id().equals(parent_question_id))
                .collect(Collectors.toList());
    }
}
