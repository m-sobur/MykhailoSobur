package com.epam.spring.homework3.quiz.controller.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AnswerVariantDto {
    private Integer variant_id;
    private String variant_title;
    private boolean userChecked;
    private Integer parent_question_id;
    private boolean isCorrect;
}
