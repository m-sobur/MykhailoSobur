package com.epam.spring.homework3.quiz.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
@Builder
public class Question {
    private UUID question_id;
    private String question_title;
    private QuestionType question_type;
    private UUID parent_quiz;
    private List<AnswerVariant> answerVariants_List;
}
