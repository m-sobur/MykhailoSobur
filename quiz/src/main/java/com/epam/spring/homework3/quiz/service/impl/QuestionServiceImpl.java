package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuestionMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import com.epam.spring.homework3.quiz.service.QuestionService;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final AnswerVariantService answerVariantService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public Question getQuestionByID(Integer id) throws NoSuchElementException {
        Question question = questionRepository.getQuestionByID(id);
        question.setAnswerVariantList(answerVariantService.getAllAnswerVariantByParentQuestionId(id));
        LOGGER.info("SERVICE LAYER: getQuestionByID method " + question);
        return question;
    }

    @Override
    public Question createQuestion(QuestionDto questionDto) throws ElementAlreadyExistException {
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        question = questionRepository.createQuestion(question);
        LOGGER.info("SERVICE LAYER: createQuestion method " + question);
        return question;
    }

    @Override
    public Question updateQuestionById(Integer id, QuestionDto questionDto) throws NoSuchElementException {
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        question = questionRepository.updateQuestionById(id, question);
        LOGGER.info("SERVICE LAYER: updateQuestionById method " + id);
        return question;
    }

    @Override
    public void deleteQuestionById(Integer id) throws NoSuchElementException {
        questionRepository.deleteQuestionById(id);
        LOGGER.info("SERVICE LAYER: deleteQuestionById " + id);
    }

    @Override
    public List<Question> getAllQuestionsByParentQuizId(Integer parentQuizId) {
        List<Question> questionList = questionRepository.getAllQuestionsByParentQuizId(parentQuizId);
       /* for (QuestionDto questionDto : questionListDto) {
            questionDto.setAnswerVariantList(answerVariantService.getAllAnswerVariantDtoByParentQuestionId(question.getQuestion_id()));
        } forEach cycle can be replaced by stream.api */
        questionList = questionList.stream()
                .peek(question -> question.setAnswerVariantList(answerVariantService.getAllAnswerVariantByParentQuestionId(question.getId())))
                .collect(Collectors.toList());

        LOGGER.info("SERVICE LAYER: getAllQuestionsByParentQuizId " + parentQuizId);
        return questionList;
    }
}