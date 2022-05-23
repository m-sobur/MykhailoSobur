package com.epam.spring.homework3.quiz.controller.dto;


import com.epam.spring.homework3.quiz.service.model.QuestionType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionDto {
    private Integer question_id;
    private String question_title;
    private QuestionType question_type;
    private Integer parent_quiz;

    private List<AnswerVariantDto> answerVariantList;
}
