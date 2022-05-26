package com.epam.spring.homework3.quiz.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class QuizDto {
    private Integer id_quiz;
    private String title;
    private Date creationDate;
    private Integer creator;
    private String quiz_type;

    private List<QuestionDto> questionList;
}
