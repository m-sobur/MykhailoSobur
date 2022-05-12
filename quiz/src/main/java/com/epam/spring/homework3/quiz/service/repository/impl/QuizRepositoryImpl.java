package com.epam.spring.homework3.quiz.service.repository.impl;

import com.epam.spring.homework3.quiz.exception.repositoryException.NoSuchQuizException;
import com.epam.spring.homework3.quiz.exception.repositoryException.QuizAlreadyExistException;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import com.epam.spring.homework3.quiz.service.repository.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class QuizRepositoryImpl implements QuizRepository {
    private final List<Quiz> temporaryDataBase = new ArrayList<>();

    @Override
    public Quiz getQuizByTitle(String title) throws NoSuchQuizException {
        Quiz resultQuiz = temporaryDataBase.stream()
                .filter(quiz -> quiz.getTitle().equals(title))
                .findAny()
                .orElseThrow(() -> new NoSuchQuizException("Quiz not found in the 'temporararyDataBase' while executing getQuizByTitle"));
        log.info("REPOSITORY LAYER: getQuizByTitle method ");
        return resultQuiz;
    }


    @Override
    public Quiz createQuiz(Quiz quiz) throws QuizAlreadyExistException {
        boolean userIsAlreadyExist = temporaryDataBase.stream()
                .anyMatch(quiz1 -> quiz1.getTitle().equals(quiz.getTitle()));
        if (userIsAlreadyExist) {
            throw new QuizAlreadyExistException("Quiz with equal title is already exist at 'temporaryDataBase' while executing createQuiz");
        } else {
            temporaryDataBase.add(quiz);
            log.info("REPOSITORY LAYER: createQuiz method ");
            return quiz;
        }
    }

    @Override
    public Quiz updateQuizByTitle(String title, Quiz quiz) throws NoSuchQuizException {
        Quiz quizToUpdate = temporaryDataBase.stream()
                .filter(quiz1 -> quiz1.getTitle().equals(title))
                .findAny()
                .orElseThrow(() -> new NoSuchQuizException("User not found in the 'temporararyDataBase' while executing updateUserByEmail"));

        quizToUpdate.setQuiz_type(quiz.getQuiz_type());
        quizToUpdate.setTitle(quiz.getTitle());
        quizToUpdate.setCreator(quiz.getCreator());
        quizToUpdate.setCreationDate(quiz.getCreationDate());
        log.info("REPOSITORY LAYER: updateQuizByTitle method ");
        return quizToUpdate;
    }

    @Override
    public void deleteQuizByTitle(String title) throws NoSuchQuizException {
        boolean isDeleted = temporaryDataBase.removeIf(quiz -> quiz.getTitle().equals(title));
        if (!isDeleted) {
            throw new NoSuchQuizException("Quiz with " + title + " not found in the 'temporararyDataBase' while executing deleteQuizByTitle");
        }
        log.info("REPOSITORY LAYER: deleteQuizByTitle method ");
    }

    @Override
    public List<Quiz> getAllQuizesByCreatorId(UUID creator) throws NoSuchQuizException {
        boolean isEmpty = temporaryDataBase.isEmpty();
        if (isEmpty) {
            throw new NoSuchQuizException("User with " + creator + "id have not any quiz  in the 'temporararyDataBase' while executing getAllQuizesByCreatorId");
        } else {
            log.info("REPOSITORY LAYER: getAllQuizesByCreatorId method ");
            return temporaryDataBase.stream()
                    .filter(quiz -> quiz.getCreator().equals(creator))
                    .collect(Collectors.toList());
        }
    }
}
