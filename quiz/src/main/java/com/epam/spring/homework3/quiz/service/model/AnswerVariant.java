package com.epam.spring.homework3.quiz.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerVariant {
    private Integer id;
    private String variantTitle;
    private Boolean userChecked;
    private Integer parentQuestionId;
    private Boolean value;
}
