package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.exception.repository.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.QuestionService;
import com.epam.spring.homework3.quiz.service.QuizService;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import com.epam.spring.homework3.quiz.service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizMapper quizMapper;
    private final QuizRepository quizRepository;
    private final QuestionService questionService;

    @Override
    public Quiz getQuizByTitle(String title) throws NoSuchElementException {
        Quiz quiz = quizRepository.getQuizByTitle(title);
        List<Question> questionList = questionService.getAllQuestionsByParentQuizId(quiz.getId());
        quiz.setQuestionList(questionList);
        log.info("SERVICE LAYER: getQuizByTitle method " + quiz);
        return quiz;
    }

    @Override
    public Quiz getQuizById(Integer id) throws NoSuchElementException {
        Quiz quiz = quizRepository.getQuizById(id);
        List<Question> questionList = questionService.getAllQuestionsByParentQuizId(quiz.getId());
        quiz.setQuestionList(questionList);
        log.info("SERVICE LAYER: getQuizById method " + quiz);
        return quiz;
    }

    @Override
    public Quiz createQuiz(QuizDto quizDto) throws ElementAlreadyExistException {
        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);
        quiz = quizRepository.createQuiz(quiz);
        log.info("SERVICE LAYER: createQuiz method " + quiz);
        return quiz;
    }

    @Override
    public Quiz updateQuizByTitle(String title, QuizDto quizDto) throws NoSuchElementException {
        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);
        quiz = quizRepository.updateQuizByTitle(title, quiz);
        log.info("SERVICE LAYER: updateQuizByTitle method " + title);
        return quiz;
    }

    @Override
    public void deleteQuizByTitle(String title) throws NoSuchElementException {
        quizRepository.deleteQuizByTitle(title);
        log.info("SERVICE LAYER: deleteQuizByTitle " + title);
    }

    @Override
    public List<Quiz> getAllQuizesByCreatorId(Integer creatorId){
        List<Quiz> quizList = quizRepository.getAllQuizesByCreatorId(creatorId);
        log.info("SERVICE LAYER: getAllQuizesByCreatorId " + creatorId);
        return quizList;
    }
}
