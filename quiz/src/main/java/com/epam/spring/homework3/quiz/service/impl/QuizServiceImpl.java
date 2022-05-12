package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.NoSuchQuizException;
import com.epam.spring.homework3.quiz.exception.repositoryException.QuizAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuizService;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import com.epam.spring.homework3.quiz.service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizMapper quizMapper;
    private final QuizRepository quizRepository;

    @Override
    public QuizDto getQuizByTitle(String title) throws NoSuchQuizException{
        Quiz quiz = quizRepository.getQuizByTitle(title);
        log.info("SERVICE LAYER: getQuizByTitle method " + title);
        return quizMapper.quizToQuizDto(quiz);
    }

    @Override
    public QuizDto createQuiz(QuizDto quizDto) throws QuizAlreadyExistException {
        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);
        quiz.setId_quiz(UUID.randomUUID());
        quiz = quizRepository.createQuiz(quiz);
        log.info("SERVICE LAYER: createQuiz method " + quiz);
        return quizMapper.quizToQuizDto(quiz);
    }

    @Override
    public QuizDto updateQuizByTitle(String title, QuizDto quizDto) throws NoSuchQuizException{
        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);
        quiz = quizRepository.updateQuizByTitle(title, quiz);
        log.info("SERVICE LAYER: updateQuizByTitle method " + title);
        return quizMapper.quizToQuizDto(quiz);
    }

    @Override
    public void deleteQuizByTitle(String title) throws NoSuchQuizException {
        quizRepository.deleteQuizByTitle(title);
        log.info("SERVICE LAYER: deleteQuizByTitle " + title);
    }

    @Override
    public List<QuizDto> getAllQuizesByCreatorId(UUID creator) throws NoSuchQuizException{
        List<Quiz> quizList = quizRepository.getAllQuizesByCreatorId(creator);
        log.info("SERVICE LAYER: getAllQuizesByCreatorId " + creator);
        return  quizMapper.quizsToQuizsDto(quizList);
    }
}
