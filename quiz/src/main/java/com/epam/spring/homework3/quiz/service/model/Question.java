package com.epam.spring.homework3.quiz.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Question {
    private Integer id;
    private String questionTitle;
    private QuestionType questionType;
    private Integer parentQuizId;

    private List<AnswerVariant> answerVariantList;
}
