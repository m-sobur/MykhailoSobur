package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuestionMapper;
import com.epam.spring.homework3.quiz.exception.repository.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import com.epam.spring.homework3.quiz.service.QuestionService;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final AnswerVariantService answerVariantService;

    @Override
    public Question getQuestionByID(Integer id) throws NoSuchElementException {
        Question question = questionRepository.getQuestionByID(id);
        question.setAnswerVariantList(answerVariantService.getAllAnswerVariantByParentQuestionId(id));
        log.info("SERVICE LAYER: getQuestionByID method " + question);
        return question;
    }

    @Override
    public Question createQuestion(QuestionDto questionDto) throws ElementAlreadyExistException {
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        question = questionRepository.createQuestion(question);
        log.info("SERVICE LAYER: createQuestion method " + question);
        return question;
    }

    @Override
    public Question updateQuestionById(Integer id, QuestionDto questionDto) throws NoSuchElementException {
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        question = questionRepository.updateQuestionById(id, question);
        log.info("SERVICE LAYER: updateQuestionById method " + id);
        return question;
    }

    @Override
    public void deleteQuestionById(Integer id) throws NoSuchElementException {
        questionRepository.deleteQuestionById(id);
        log.info("SERVICE LAYER: deleteQuestionById " + id);
    }

    @Override
    public List<Question> getAllQuestionsByParentQuizId(Integer parentQuizId) {
        List<Question> questionList = questionRepository.getAllQuestionsByParentQuizId(parentQuizId);

        for (Question question : questionList) {
            question.setAnswerVariantList(answerVariantService.getAllAnswerVariantByParentQuestionId(question.getId()));
        }

        log.info("SERVICE LAYER: getAllQuestionsByParentQuizId " + parentQuizId);
        return questionList;
    }
}
