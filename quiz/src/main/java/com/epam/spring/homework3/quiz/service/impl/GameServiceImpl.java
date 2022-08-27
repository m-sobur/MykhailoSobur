package com.epam.spring.homework3.quiz.service.impl;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import com.epam.spring.homework3.quiz.controller.mapper.QuizMapper;
import com.epam.spring.homework3.quiz.service.GameService;
import com.epam.spring.homework3.quiz.service.QuizService;
import com.epam.spring.homework3.quiz.service.model.AnswerVariant;
import com.epam.spring.homework3.quiz.service.model.Question;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final QuizService quizService;
    private final QuizMapper quizMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public QuizDto startGame(Integer id) throws NoSuchElementException {
        QuizDto quizDto = quizMapper.quizToQuizDto(quizService.getQuizById(id));
        LOGGER.info("SERVICE LAYER: startGame method " + quizDto);
        return quizDto;
    }

    @Override
    public String checkResultOfGame(QuizDto quizDto, Integer id, String userName) throws NoSuchElementException {
        Quiz etalon = quizService.getQuizById(id);
        Quiz userResult = quizMapper.quizDtoToQuiz(quizDto);
        int resultMark = 0;

        int numberOfQuesitonsInQuiz = etalon.getQuestionList().size();
        LOGGER.info("SERVICE LAYER: correct answers " + numberOfQuesitonsInQuiz);

        List<Question> userResultQuestionList = userResult.getQuestionList();
        LOGGER.info("SERVICE LAYER: userResultQuestionList " + userResultQuestionList);

        List<Question> etalonQuestionList = etalon.getQuestionList();
        LOGGER.info("SERVICE LAYER: etalonQuestionList " + etalonQuestionList);

        for (int i = 0; i < numberOfQuesitonsInQuiz; i++) {

            Question questionUser = userResultQuestionList.get(i);
            Question questionEtalon = etalonQuestionList.get(i);

            List<AnswerVariant> userResultAnswerVariantList = questionUser.getAnswerVariantList();
            List<AnswerVariant> etalonAnswerVariantList = questionEtalon.getAnswerVariantList();

            int numberOfAnswersInQuestion = etalonAnswerVariantList.size();

            for (int j = 0; j < numberOfAnswersInQuestion; j++) {
                AnswerVariant answerVariantUser = userResultAnswerVariantList.get(j);
                AnswerVariant answerVariantEtalon = etalonAnswerVariantList.get(j);

                if (Boolean.TRUE.equals(answerVariantUser.getUserChecked())
                        && Boolean.TRUE.equals(answerVariantEtalon.getValue())) {
                    resultMark++;
                }
            }
        }

        LOGGER.info("SERVICE LAYER: correct answers " + resultMark);
        resultMark = (resultMark * 100) / numberOfQuesitonsInQuiz;

        return userName + " have " + resultMark + " % correct answers";
    }
}
