package com.epam.spring.homework3.quiz.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
@Builder
public class Question {
    private Integer question_id;
    private String question_title;
    private QuestionType question_type;
    private Integer parent_quiz;

    private List<AnswerVariant> answerVariantList;
}
