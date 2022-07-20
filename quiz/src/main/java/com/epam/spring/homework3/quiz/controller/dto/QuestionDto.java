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

    @Null(message = "{common.id.absent.exception}", groups = OnUpdate.class)
    @NotNull(message = "{common.id.empty.exception}", groups = OnCreate.class)
    private Integer id;

    @NotBlank(message = "{common.title.empty.exception}", groups = {OnCreate.class, OnUpdate.class})
    private String questionTitle;

    @NotNull(message = "{common.type.empty.exception}", groups = {OnCreate.class, OnUpdate.class})
    private QuestionType questionType;

    @Null(message = "{question.parentQuizId.absent.exception}", groups = OnUpdate.class)
    @NotNull(message = "{question.parentQuizId.empty.exception}", groups = OnCreate.class)
    private Integer parentQuizId;

    private List<AnswerVariantDto> answerVariantList;
}
