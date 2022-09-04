package com.epam.spring.homework3.quiz.controller.dto;

import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerVariantDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotBlank(message = "{common.title.empty.exception}", groups = OnCreate.class)
    private String variantTitle;

    @Null(message = "{answerVariant.userChecked.empty.exception}", groups = OnCreate.class)
    private Boolean userChecked;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "{answerVariant.parentQuestionId.empty.exception}", groups = OnCreate.class)
    private Long questionId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties("answerVariantList")
    private QuestionDto question;

    @NotNull(message = "{answerVariant.value.empty.exception}", groups = OnCreate.class)
    private Boolean value;
}
