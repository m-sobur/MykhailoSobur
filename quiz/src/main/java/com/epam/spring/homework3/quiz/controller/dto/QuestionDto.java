package com.epam.spring.homework3.quiz.controller.dto;


import com.epam.spring.homework3.quiz.service.model.QuestionType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class QuestionDto {
    private UUID question_id;
    private String question_title;
    private QuestionType question_type;
    private UUID parent_quiz;
    private List<AnswerVariantDto> answerVariantsDto_List;
}
