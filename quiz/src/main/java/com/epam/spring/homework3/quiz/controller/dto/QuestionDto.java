package com.epam.spring.homework3.quiz.controller.dto;

import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.quiz.service.model.QuestionType;
import com.epam.spring.homework3.quiz.service.model.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotBlank(message = "{common.title.empty.exception}", groups = {OnCreate.class, OnUpdate.class})
    private String questionTitle;

    @NotNull(message = "{common.type.empty.exception}", groups = {OnCreate.class, OnUpdate.class})
    private QuestionType questionType;

    @Null(message = "{question.parentQuizId.absent.exception}", groups = OnUpdate.class)
    @NotNull(message = "{question.parentQuizId.empty.exception}", groups = OnCreate.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long quizId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties("questionList")
    private QuizDto quiz;

    @JsonIgnoreProperties("question")
    private List<AnswerVariantDto> answerVariantList;
}
