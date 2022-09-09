package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuestionMapper;
import com.epam.spring.homework3.quiz.exception.repository.ElementAlreadyExistException;
import com.epam.spring.homework3.quiz.service.AnswerVariantService;
import com.epam.spring.homework3.quiz.service.QuestionService;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import com.epam.spring.homework3.quiz.service.repository.QuestionRepository;
import com.epam.spring.homework3.quiz.service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final AnswerVariantService answerVariantService;
    private final QuizRepository quizRepository;

    @Override
    public Question getQuestionById(Long id) throws NoSuchElementException {
        log.info("SERVICE LAYER: getQuestionByID method entry " + id);

        Question question = questionRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Question with " + id + " - id not found at DB"));

        log.info("SERVICE LAYER: getQuestionByID method exit " + question);
        return question;
    }

    @Override
    @Transactional
    public Question createQuestion(QuestionDto questionDto) throws ElementAlreadyExistException {
        log.info("SERVICE LAYER: createQuestion method entry " + questionDto);

        Question question = questionMapper.questionDtoToQuestion(questionDto);
        Long quizId = questionDto.getQuizId();

        Quiz quiz = quizRepository
                .findById(quizId)
                .orElseThrow(() -> new NoSuchElementException("Quiz not found for linking with question"));

        question.setQuiz(quiz);
        questionRepository.save(question);

        log.info("SERVICE LAYER: createQuestion method exit " + question);
        return question;
    }

    @Override
    @Transactional
    public Question updateQuestionById(Long id, QuestionDto questionDto) throws NoSuchElementException {
        log.info("SERVICE LAYER: updateQuestionById method entry " + id);

        Question question = questionMapper.questionDtoToQuestion(questionDto);
        Question questionToUpdate = questionRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Question not found in the 'PostgresDB' while executing updateQuestionById"));

        questionToUpdate.setQuestionTitle(question.getQuestionTitle());
        questionToUpdate.setQuestionType(question.getQuestionType());

        log.info("SERVICE LAYER: updateQuestionById method exit " + question);
        return question;
    }

    @Override
    @Transactional
    public void deleteQuestionById(Long id) throws NoSuchElementException {
        log.info("SERVICE LAYER: deleteQuestionById entry " + id);

        questionRepository.deleteById(id);

        log.info("SERVICE LAYER: deleteQuestionById exit " + id);
    }

    @Override
    public List<Question> getAllQuestionsByParentQuizId(Long quizId, Pageable pageable) {
        log.info("SERVICE LAYER: getAllQuestionsByParentQuizId entry " + quizId);

        if (quizRepository.existsById(quizId)) {
            List<Question> questionList = questionRepository.findQuestionByQuizId(quizId, pageable);

            log.info("SERVICE LAYER: getAllQuestionsByParentQuizId exit " + quizId);
            return questionList;
        } else {
            throw new NoSuchElementException("quiz with " + quizId + "doesn't exsist at DB");
        }
    }
}
