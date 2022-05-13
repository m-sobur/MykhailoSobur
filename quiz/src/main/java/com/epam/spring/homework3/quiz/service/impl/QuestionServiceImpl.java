package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuestionMapper;
import com.epam.spring.homework3.quiz.service.QuestionService;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;

    @Override
    public QuestionDto getQuestionByID(UUID question_id) {
        Question question = questionRepository.getQuestionByID(question_id);
        log.info("SERVICE LAYER: getQuestionByID method " + question_id);
        return questionMapper.questionToQuestionDto(question);
    }

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        question.setQuestion_id(UUID.randomUUID());
        question = questionRepository.createQuestion(question);
        log.info("SERVICE LAYER: createQuestion method " + question);
        return questionMapper.questionToQuestionDto(question);
    }

    @Override
    public QuestionDto updateQuestionById(UUID question_id, QuestionDto questionDto) {
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        question = questionRepository.updateQuestionById(question_id, question);
        log.info("SERVICE LAYER: updateQuestionById method " + question_id);
        return questionMapper.questionToQuestionDto(question);
    }

    @Override
    public void deleteQuestionById(UUID question_id) {
        questionRepository.deleteQuestionById(question_id);
        log.info("SERVICE LAYER: deleteQuestionById " + question_id);
    }

    @Override
    public List<QuestionDto> getAllQuestionsByParentQuizId(UUID parent_quiz) {
        List<Question> questionList = questionRepository.getAllQuestionsByParentQuizId(parent_quiz);
        log.info("SERVICE LAYER: getAllQuestionsByParentQuizId " + parent_quiz);
        return  questionMapper.questionListToQuestionListDto(questionList);
    }
}

