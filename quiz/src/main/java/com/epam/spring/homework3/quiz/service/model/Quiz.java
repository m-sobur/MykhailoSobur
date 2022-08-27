package com.epam.spring.homework3.quiz.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class Quiz {
    private Integer id;
    private String title;
    private Date creationDate;
    private Integer creator;
    private String quizType;
    private List<Question> questionList;
}
