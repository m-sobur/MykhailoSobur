package com.epam.spring.homework3.quiz.controller.dto;

import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerVariantDto {

    @NotNull(message = "{common.id.empty.exception}", groups = OnCreate.class)
    private Integer id;

    @NotBlank(message = "{common.title.empty.exception}", groups = OnCreate.class)
    private String variantTitle;

    @Null(message = "{answerVariant.userChecked.empty.exception}", groups = OnCreate.class)
    private Boolean userChecked;

    @NotNull(message = "{answerVariant.parentQuestionId.empty.exception}", groups = OnCreate.class)
    private Integer parentQuestionId;

    @NotNull(message = "{answerVariant.value.empty.exception}", groups = OnCreate.class)
    private Boolean value;
}
