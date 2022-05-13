package com.epam.spring.homework3.quiz.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class QuizDto {
    private UUID id_quiz;
    private String title;
    private Date creationDate;
    private UUID creator;
    private String quiz_type;
    private List<QuestionDto> questionDtoList;
}
