package com.epam.spring.homework3.quiz.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AnswerVariantDto {
    private UUID variant_id;
    private String variant_title;
    private boolean userChecked;
    private UUID parent_question_id;
    private boolean is_correct;
}
