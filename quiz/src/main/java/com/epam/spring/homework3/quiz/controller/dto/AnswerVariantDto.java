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

    @NotNull(message = "id shouldn't be empty", groups = OnCreate.class)
    private Integer id;

    @NotBlank(message = "variantTitle shouldn't be empty", groups = OnCreate.class)
    private String variantTitle;

    @Null(message = "userChecked shouldn't be empty", groups = OnCreate.class)
    private Boolean userChecked;

    @NotNull(message = "parentQuestionId shouldn't be empty", groups = OnCreate.class)
    private Integer parentQuestionId;

    @NotNull(message = "value shouldn't be empty", groups = OnCreate.class)
    private Boolean value;
}
