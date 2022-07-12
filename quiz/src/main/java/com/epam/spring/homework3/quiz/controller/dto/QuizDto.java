package com.epam.spring.homework3.quiz.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class QuizDto {
    private Integer id;
    private String title;
    private Date creationDate;
    private Integer creatorId;
    private String quizType;

    private List<QuestionDto> questionList;
}
