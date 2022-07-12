package com.epam.spring.homework3.quiz.controller.dto;


import com.epam.spring.homework3.quiz.service.model.QuestionType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionDto {
    private Integer id;
    private String questionTitle;
    private QuestionType questionType;
    private Integer parentQuizId;

    private List<AnswerVariantDto> answerVariantList;
}
