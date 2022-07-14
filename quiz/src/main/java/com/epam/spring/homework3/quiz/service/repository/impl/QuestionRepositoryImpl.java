package com.epam.spring.homework3.quiz.service.repository.impl;

import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class QuestionRepositoryImpl implements QuestionRepository {
    private final List<Question> temporaryDataBase = new ArrayList<>();

    @Override
    public Question getQuestionByID(Integer id) throws NoSuchElementException {
        Question resultQuestion = temporaryDataBase.stream()
                .filter(question -> question.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Question not found in the 'temporararyDataBase' while executing getQuestionByID"));

        log.info("REPOSITORY LAYER: getQuestionByID method ");
        return resultQuestion;
    }

    @Override
    public Question createQuestion(Question question) throws ElementAlreadyExistException {
        boolean questionIsAlreadyExist = temporaryDataBase.stream()
                .anyMatch(question1 -> question1.getQuestionTitle().equals(question.getQuestionTitle()));

        if (questionIsAlreadyExist) {
            throw new ElementAlreadyExistException("Question with equal title is already exist at 'temporaryDataBase' while executing createQuestion");
        } else {
            temporaryDataBase.add(question);
            log.info("REPOSITORY LAYER: createQuestion method ");
            return question;
        }
    }

    @Override
    public Question updateQuestionById(Integer id, Question question) throws NoSuchElementException {
        Question questionToUpdate = temporaryDataBase.stream()
                .filter(question1 -> question1.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Question not found in the 'temporararyDataBase' while executing updateQuestionById"));

        questionToUpdate.setQuestionTitle(question.getQuestionTitle());
        questionToUpdate.setQuestionType(question.getQuestionType());
        questionToUpdate.setParentQuiz(question.getParentQuiz());

        log.info("REPOSITORY LAYER: updateQuestionById method ");
        return questionToUpdate;
    }

    @Override
    public void deleteQuestionById(Integer id) throws NoSuchElementException {
        boolean isDeleted = temporaryDataBase.removeIf(question -> question.getId().equals(id));

        if (!isDeleted) {
            throw new NoSuchElementException("Question with " + id + " not found in the 'temporararyDataBase' while executing deleteQuestionById");
        }

        log.info("REPOSITORY LAYER: deleteQuizByTitle method ");
    }

    @Override
    public List<Question> getAllQuestionsByParentQuizId(Integer parentQuizId) {
        log.info("REPOSITORY LAYER: getAllQuestionsByParentQuizId method ");
        return temporaryDataBase.stream()
                .filter(question -> question.getParentQuiz().equals(parentQuizId))
                .collect(Collectors.toList());
    }
}
