package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuestionService;
import com.epam.spring.homework3.quiz.service.QuizService;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import com.epam.spring.homework3.quiz.service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizMapper quizMapper;
    private final QuizRepository quizRepository;
    private final QuestionService questionService;

    @Override
    public QuizDto getQuizByTitle(String title) throws NoSuchElementException {
        Quiz quiz = quizRepository.getQuizByTitle(title);
        quiz.setQuestionList(questionService.getAllQuestionsByParentQuizId(quiz.getId_quiz()));
        log.info("SERVICE LAYER: getQuizByTitle method " + quiz);
        return quizMapper.quizToQuizDto(quiz);
    }

//    @Override
//    public QuizDto getQuizById(Integer id_quiz) throws NoSuchElementException {
//        Quiz quiz = quizRepository.getQuizByTitle(id_quiz);
//        quiz.setQuestionList(questionService.getAllQuestionsByParentQuizId(quiz.getId_quiz()));
//        log.info("SERVICE LAYER: getQuizById method " + quiz);
//        return quizMapper.quizToQuizDto(quiz);
//    }

    @Override
    public QuizDto createQuiz(QuizDto quizDto) throws ElementAlreadyExistException {
        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);
        quiz = quizRepository.createQuiz(quiz);
        log.info("SERVICE LAYER: createQuiz method " + quiz);
        return quizMapper.quizToQuizDto(quiz);
    }

    @Override
    public QuizDto updateQuizByTitle(String title, QuizDto quizDto) throws NoSuchElementException{
        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);
        quiz = quizRepository.updateQuizByTitle(title, quiz);
        log.info("SERVICE LAYER: updateQuizByTitle method " + title);
        return quizMapper.quizToQuizDto(quiz);
    }

    @Override
    public void deleteQuizByTitle(String title) throws NoSuchElementException {
        quizRepository.deleteQuizByTitle(title);
        log.info("SERVICE LAYER: deleteQuizByTitle " + title);
    }

    @Override
    public List<QuizDto> getAllQuizesByCreatorId(Integer creator) throws NoSuchElementException{
        List<Quiz> quizList = quizRepository.getAllQuizesByCreatorId(creator);
        log.info("SERVICE LAYER: getAllQuizesByCreatorId " + creator);
        return  quizMapper.quizsToQuizsDto(quizList);
    }
}
