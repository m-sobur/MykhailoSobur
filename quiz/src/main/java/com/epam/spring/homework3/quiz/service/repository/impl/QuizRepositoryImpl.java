package com.epam.spring.homework3.quiz.service.repository.impl;

import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import com.epam.spring.homework3.quiz.service.repository.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class QuizRepositoryImpl implements QuizRepository {
    private final List<Quiz> temporaryDataBase = new ArrayList<>();

    @Override
    public Quiz getQuizByTitle(String title) throws NoSuchElementException {
        Quiz resultQuiz = temporaryDataBase.stream()
                .filter(quiz -> quiz.getTitle().equals(title))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Quiz not found in the 'temporararyDataBase' while executing getQuizByTitle"));

        log.info("REPOSITORY LAYER: getQuizByTitle method ");
        return resultQuiz;
    }

    @Override
    public Quiz getQuizById(Integer id) throws NoSuchElementException {
        Quiz resultQuiz = temporaryDataBase.stream()
                .filter(quiz -> quiz.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Quiz not found in the 'temporararyDataBase' while executing getQuizById"));

        log.info("REPOSITORY LAYER: getQuizById method ");
        return resultQuiz;
    }

    @Override
    public Quiz createQuiz(Quiz quiz) throws ElementAlreadyExistException {
        boolean quizIsAlreadyExist = temporaryDataBase.stream()
                .anyMatch(quiz1 -> quiz1.getTitle().equals(quiz.getTitle()));

        if (quizIsAlreadyExist) {
            throw new ElementAlreadyExistException("Quiz with equal title is already exist at 'temporaryDataBase' while executing createQuiz");
        } else {
            temporaryDataBase.add(quiz);
            log.info("REPOSITORY LAYER: createQuiz method ");
            return quiz;
        }
    }

    @Override
    public Quiz updateQuizByTitle(String title, Quiz quiz) throws NoSuchElementException {
        Quiz quizToUpdate = temporaryDataBase.stream()
                .filter(quiz1 -> quiz1.getTitle().equals(title))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Quiz not found in the 'temporararyDataBase' while executing updateUserByEmail"));

        quizToUpdate.setQuizType(quiz.getQuizType());
        quizToUpdate.setTitle(quiz.getTitle());
        quizToUpdate.setCreator(quiz.getCreator());
        quizToUpdate.setCreationDate(new Date());

        log.info("REPOSITORY LAYER: updateQuizByTitle method ");
        return quizToUpdate;
    }

    @Override
    public void deleteQuizByTitle(String title) throws NoSuchElementException {
        boolean isDeleted = temporaryDataBase.removeIf(quiz -> quiz.getTitle().equals(title));

        if (!isDeleted) {
            throw new NoSuchElementException("Quiz with " + title + " not found in the 'temporararyDataBase' while executing deleteQuizByTitle");
        }

        log.info("REPOSITORY LAYER: deleteQuizByTitle method ");
    }

    @Override
    public List<Quiz> getAllQuizesByCreatorId(Integer creatorId) {
        log.info("REPOSITORY LAYER: getAllQuizesByCreatorId method ");
        return temporaryDataBase.stream()
                .filter(quiz -> quiz.getCreator().equals(creatorId))
                .collect(Collectors.toList());
    }
}