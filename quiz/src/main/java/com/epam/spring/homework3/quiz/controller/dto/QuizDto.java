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

    @Null(message = "{common.id.absent.exception}", groups = OnUpdate.class)
    @NotNull(message = "{common.id.empty.exception}", groups = OnCreate.class)
    private Integer id;

    @NotBlank(message = "{common.title.empty.exception}", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Null(message = "{quiz.creationDate.absent.exception}", groups = {OnCreate.class, OnUpdate.class})
    private Date creationDate;

    @NotNull(message = "{quiz.creatorId.empty.exception}", groups = OnCreate.class)
    @Null(message = "{quiz.creatorId.absent.exception}", groups = OnUpdate.class)
    private Integer creatorId;

    @NotBlank(message = "{common.type.empty.exception}", groups = {OnCreate.class, OnUpdate.class})
    private String quizType;

    private List<QuestionDto> questionList;
}
