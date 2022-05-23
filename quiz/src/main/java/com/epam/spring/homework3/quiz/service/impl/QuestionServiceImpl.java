package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.controller.mapper.AnswerVariantMapper;
import com.epam.spring.homework3.quiz.controller.mapper.QuestionMapper;
import com.epam.spring.homework3.quiz.exception.repositoryException.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import com.epam.spring.homework3.quiz.service.QuestionService;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final AnswerVariantService answerVariantService;

    @Override
    public QuestionDto getQuestionByID(Integer question_id) throws NoSuchElementException {
        Question question = questionRepository.getQuestionByID(question_id);
        question.setAnswerVariantList(answerVariantService.getAllAnswerVariantByParentQuestionId(question_id));
        log.info("SERVICE LAYER: getQuestionByID method " + question);
        return questionMapper.questionToQuestionDto(question);
    }

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) throws ElementAlreadyExistException {
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        question = questionRepository.createQuestion(question);
        log.info("SERVICE LAYER: createQuestion method " + question);
        return questionMapper.questionToQuestionDto(question);
    }

    @Override
    public QuestionDto updateQuestionById(Integer question_id, QuestionDto questionDto) throws NoSuchElementException{
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        question = questionRepository.updateQuestionById(question_id, question);
        log.info("SERVICE LAYER: updateQuestionById method " + question_id);
        return questionMapper.questionToQuestionDto(question);
    }


    @Override
    public void deleteQuestionById(Integer question_id) throws NoSuchElementException{
        questionRepository.deleteQuestionById(question_id);
        log.info("SERVICE LAYER: deleteQuestionById " + question_id);
    }

    @Override
    public List<QuestionDto> getAllQuestionsDtoByParentQuizId(Integer parent_quiz) throws NoSuchElementException{
        List<Question> questionList = questionRepository.getAllQuestionsByParentQuizId(parent_quiz);
        log.info("SERVICE LAYER: getAllQuestionsByParentQuizId " + parent_quiz);
        return questionMapper.questionListToQuestionListDto(questionList);
    }

    @Override
    public List<Question> getAllQuestionsByParentQuizId(Integer parent_quiz) throws NoSuchElementException{
        List<Question> questionList = questionRepository.getAllQuestionsByParentQuizId(parent_quiz);
       /* for (Question question : questionList) {
            question.setAnswerVariantList(answerVariantService.getAllAnswerVariantByParentQuestionId(question.getQuestion_id()));
        } forEach cycle can be replaced by stream.api */
        questionList = questionList.stream()
                .peek(question -> question.setAnswerVariantList(answerVariantService.getAllAnswerVariantByParentQuestionId(question.getQuestion_id())))
                .collect(Collectors.toList());

        log.info("SERVICE LAYER: getAllQuestionsByParentQuizId " + parent_quiz);
        return questionList;
    }
}

