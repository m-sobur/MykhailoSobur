package com.epam.spring.homework3.quiz.service.repository.impl;

import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class QuestionRepositoryImpl implements QuestionRepository {
    private final List<Question> temporaryDataBase = new ArrayList<>();

    @Override
    public Question getQuestionByID(UUID question_id) {
        Question resultQuestion = temporaryDataBase.stream()
                .filter(question -> question.getQuestion_id().equals(question_id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Question not found in the 'temporararyDataBase' while executing getQuestionByID"));
        log.info("REPOSITORY LAYER: getQuestionByID method ");
        return resultQuestion;
    }

    @Override
    public Question createQuestion(Question question) {
        boolean userIsAlreadyExist = temporaryDataBase.stream()
                .anyMatch(question1 -> question1.getQuestion_title().equals(question.getQuestion_title()));
        if (userIsAlreadyExist) {
            throw new ElementAlreadyExistException("Question with equal title is already exist at 'temporaryDataBase' while executing createQuestion");
        } else {
            temporaryDataBase.add(question);
            log.info("REPOSITORY LAYER: createQuestion method ");
            return question;
        }
    }

    @Override
    public Question updateQuestionById(UUID question_id, Question question) {
        Question questionToUpdate = temporaryDataBase.stream()
                .filter(question1 -> question1.getQuestion_id().equals(question_id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Question not found in the 'temporararyDataBase' while executing updateQuestionById"));

        questionToUpdate.setQuestion_title(question.getQuestion_title());
        questionToUpdate.setQuestion_type(question.getQuestion_type());
        questionToUpdate.setParent_quiz(question.getParent_quiz());
        log.info("REPOSITORY LAYER: updateQuestionById method ");
        return questionToUpdate;
    }

    @Override
    public void deleteQuestionById(UUID question_id) {
        boolean isDeleted = temporaryDataBase.removeIf(question -> question.getQuestion_id().equals(question_id));
        if (!isDeleted) {
            throw new NoSuchElementException("Question with " + question_id + " not found in the 'temporararyDataBase' while executing deleteQuestionById");
        }
        log.info("REPOSITORY LAYER: deleteQuizByTitle method ");
    }

    @Override
    public List<Question> getAllQuestionsByParentQuizId(UUID parent_quiz) {
        boolean isEmpty = temporaryDataBase.isEmpty();
        if (isEmpty) {
            throw new NoSuchElementException("Quiz with " + parent_quiz + "id have not any question  in the 'temporararyDataBase' while executing getAllQuestionsByParentQuizId");
        } else {
            log.info("REPOSITORY LAYER: getAllQuestionsByParentQuizId method ");
            return temporaryDataBase.stream()
                    .filter(question -> question.getParent_quiz().equals(parent_quiz))
                    .collect(Collectors.toList());
        }
    }
}
