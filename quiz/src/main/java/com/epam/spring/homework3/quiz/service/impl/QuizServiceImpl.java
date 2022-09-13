package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.exception.repository.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuizService;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import com.epam.spring.homework3.quiz.service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizServiceImpl implements QuizService {
    private final QuizMapper quizMapper;
    private final QuizRepository quizRepository;

    @Override
    public Quiz getQuizByTitle(String title) throws NoSuchElementException {
        log.info("SERVICE LAYER: getQuizByTitle method entry " + title);

        Quiz quiz = quizRepository
                .findQuizByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Quiz with " + title + " - title not found at DB"));

        log.info("SERVICE LAYER: getQuizByTitle method exit " + quiz);
        return quiz;
    }

    @Override
    public Quiz getQuizById(Long id) throws NoSuchElementException {
        log.info("SERVICE LAYER: getQuizById method entry " + id);

        Quiz quiz = quizRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Quiz with " + id + " - id not found at DB"));

        log.info("SERVICE LAYER: getQuizById method exit " + quiz);
        return quiz;
    }

    @Override
    @Transactional
    public Quiz createQuiz(QuizDto quizDto) throws ElementAlreadyExistException {
        log.info("SERVICE LAYER: createQuiz method entry " + quizDto);

        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);

        if (!quizRepository.existsByTitle(quizDto.getTitle())) {
            quiz.setCreationDate(new Date());
            quizRepository.save(quiz);
        } else {
            throw new ElementAlreadyExistException("Quiz with " + quizDto.getTitle() + " already exist in DB ");
        }

        log.info("SERVICE LAYER: createQuiz method exit " + quiz);
        return quiz;
    }

    @Override
    @Transactional
    public Quiz updateQuizByTitle(String title, QuizDto quizDto) throws NoSuchElementException {
        log.info("SERVICE LAYER: updateQuizByTitle method entry " + title);

        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);

        Quiz quizToUpdate = quizRepository
                .findQuizByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Quiz with " + title + " not found in the 'PostgresDB' while executing updateQuizByTitle"));

        quizToUpdate.setTitle(quiz.getTitle());
        quizToUpdate.setQuizType(quiz.getQuizType());

        log.info("SERVICE LAYER: updateQuizByTitle method exit " + title);
        return quiz;
    }

    @Override
    @Transactional
    public void deleteQuizByTitle(String title) throws NoSuchElementException {
        log.info("SERVICE LAYER: deleteQuizByTitle entry " + title);

        if (quizRepository.existsByTitle(title)) {
            quizRepository.deleteByTitle(title);
        } else {
            throw new NoSuchElementException("Quiz with " + title + " not found in DB ");
        }

        log.info("SERVICE LAYER: deleteQuizByTitle exit " + title);
    }

    @Override
    public List<Quiz> getAllQuizByCreatorId(Long creatorId, Pageable pageable) {
        log.info("SERVICE LAYER: getAllQuizesByCreatorId entry " + creatorId);

        List<Quiz> quizList = quizRepository.findQuizByCreatorId(creatorId, pageable);

        log.info("SERVICE LAYER: getAllQuizesByCreatorId exit " + quizList);
        return quizList;
    }
}
