package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.service.GameService;
import com.epam.spring.homework3.quiz.service.QuizService;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final QuizService quizService;
    private final QuizMapper quizMapper;

    @Override
    public QuizDto startGame(Integer id_quiz) throws NoSuchElementException {
        QuizDto quizDto = quizService.getQuizById(id_quiz);
        log.info("SERVICE LAYER: startGame method " + quizDto);
        return quizDto;
    }

    @Override
    public String checkResultOfGame(QuizDto quizDto, Integer id_quiz, String userName) throws NoSuchElementException {
        Quiz etalon = quizMapper.quizDtoToQuiz(quizService.getQuizById(id_quiz));
        Quiz userResult = quizMapper.quizDtoToQuiz(quizDto);

        int numberOfQuesitonsInQuiz = etalon.getQuestionList().size();
        log.info("SERVICE LAYER: numberOfQuesitonsInQuiz " + numberOfQuesitonsInQuiz);

        int resultMark = 0;

        List<Question> userResultQuestionList = userResult.getQuestionList();
        log.info("SERVICE LAYER: userResultQuestionList " + userResultQuestionList);

        List<Question> etalonQuestionList = etalon.getQuestionList();
        log.info("SERVICE LAYER: etalonQuestionList " + etalonQuestionList);
        for (int i = 0; i < numberOfQuesitonsInQuiz; i++) {

            Question questionUser = userResultQuestionList.get(i);
            Question questionEtalon = etalonQuestionList.get(i);

            List<AnswerVariant> userResultAnswerVariantList = questionUser.getAnswerVariantList();
            List<AnswerVariant> etalonAnswerVariantList = questionEtalon.getAnswerVariantList();

            int numberOfAnswersInQuestion = etalonQuestionList.size();

            for (int j = 0; j < numberOfAnswersInQuestion; j++) {
                AnswerVariant answerVariantUser = userResultAnswerVariantList.get(j);
                AnswerVariant answerVariantEtalon = etalonAnswerVariantList.get(j);

                if (Boolean.TRUE.equals(answerVariantUser.getUserChecked())
                        && Boolean.TRUE.equals(answerVariantEtalon.getValue())) {
                        resultMark++;
                }
            }
        }
        log.info("SERVICE LAYER: correct answers " + resultMark);
        resultMark = (resultMark * 100) / numberOfQuesitonsInQuiz;

        return userName + " have " + resultMark + " % correct answers";
    }
}
