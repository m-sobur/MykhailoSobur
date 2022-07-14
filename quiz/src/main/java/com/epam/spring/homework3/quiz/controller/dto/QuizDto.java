package com.epam.spring.homework3.quiz.controller.dto;

import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizDto {

    @Null(message = "id should be absent in request", groups = OnUpdate.class)
    @NotNull(message = "id shouldn't be empty", groups = OnCreate.class)
    private Integer id;

    @NotBlank(message = "title shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Null(message = "creationDate should be absent in request", groups = {OnCreate.class, OnUpdate.class})
    private Date creationDate;

    @NotNull(message = "creatorId shouldn't be empty", groups = OnCreate.class)
    @Null(message = "creatorId should be absent in request", groups = OnUpdate.class)
    private Integer creatorId;

    @NotBlank(message = "quizType shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String quizType;

    private List<QuestionDto> questionList;
}
