package com.epam.spring.homework3.quiz.controller.dto;

import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.quiz.service.model.QuestionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {

    @Null(message = "id should be absent in request", groups = OnUpdate.class)
    @NotNull(message = "id shouldn't be empty", groups = OnCreate.class)
    private Integer id;

    @NotBlank(message = "questionTitle shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private String questionTitle;

    @NotNull(message = "questionType shouldn't be empty", groups = {OnCreate.class, OnUpdate.class})
    private QuestionType questionType;

    @Null(message = "parentQuizId should be absent in request", groups = OnUpdate.class)
    @NotNull(message = "parentQuizId shouldn't be empty", groups = OnCreate.class)
    private Integer parentQuizId;

    private List<AnswerVariantDto> answerVariantList;
}
