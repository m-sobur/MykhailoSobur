package com.epam.spring.homework3.quiz.controller.dto;

import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuizDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotBlank(message = "{common.title.empty.exception}", groups = {OnCreate.class, OnUpdate.class})
    private String title;

    @Null(message = "{quiz.creationDate.absent.exception}", groups = {OnCreate.class, OnUpdate.class})
    private Date creationDate;

    @NotNull(message = "{quiz.creatorId.empty.exception}", groups = OnCreate.class)
    @Null(message = "{quiz.creatorId.absent.exception}", groups = OnUpdate.class)
    private Long creatorId;

    @NotBlank(message = "{common.type.empty.exception}", groups = {OnCreate.class, OnUpdate.class})
    private String quizType;

    @JsonIgnoreProperties("quiz")
    private List<QuestionDto> questionList;
}
