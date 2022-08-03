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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Quiz getQuizByTitle(String title) throws NoSuchElementException {
        log.info("SERVICE LAYER: getQuizByTitle method entry " + title);

        Quiz quiz = quizRepository
                .findQuizByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Quiz with " + title + " - title not found at DB"));

        List<Question> questionList = questionService.getAllQuestionsByParentQuizId(quiz.getId());
        quiz.setQuestionList(questionList);
        log.info("SERVICE LAYER: getQuizByTitle method exit " + quiz);
        return quiz;
    }

    @Override
    @Transactional
    public Quiz getQuizById(Long id) throws NoSuchElementException {
        log.info("SERVICE LAYER: getQuizById method entry " + id);

        Quiz quiz = quizRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Quiz with " + id + " - id not found at DB"));

        List<Question> questionList = questionService.getAllQuestionsByParentQuizId(quiz.getId());
        quiz.setQuestionList(questionList);
        log.info("SERVICE LAYER: getQuizById method exit " + quiz);
        return quiz;
    }

    @Override
    @Transactional
    public Quiz createQuiz(QuizDto quizDto) throws ElementAlreadyExistException {
        log.info("SERVICE LAYER: createQuiz method entry " + quizDto);
        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);
        quizRepository.save(quiz);
        log.info("SERVICE LAYER: createQuiz method exit " + quiz);
        return quiz;
    }

//    @Override
//    public Quiz updateQuizByTitle(String title, QuizDto quizDto) throws NoSuchElementException {
//        Quiz quiz = quizMapper.quizDtoToQuiz(quizDto);
//        quiz = quizRepository.updateQuizByTitle(title, quiz);
//        log.info("SERVICE LAYER: updateQuizByTitle method " + title);
//        return quiz;
//    }

    @Override
    @Transactional
    public void deleteQuizByTitle(String title) throws NoSuchElementException {
        log.info("SERVICE LAYER: deleteQuizByTitle entry " + title);
        quizRepository.deleteByTitle(title);
        log.info("SERVICE LAYER: deleteQuizByTitle exit " + title);
    }

    @Override
    @Transactional
    public List<Quiz> getAllQuizesByCreatorId(Long creatorId){
        List<Quiz> quizList = quizRepository.findAll();
        log.info("SERVICE LAYER: getAllQuizesByCreatorId " + creatorId);
        return quizList;
    }
}
