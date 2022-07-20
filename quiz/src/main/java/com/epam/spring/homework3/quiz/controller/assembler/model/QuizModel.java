package com.epam.spring.homework3.quiz.controller.assembler.model;

import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class QuizModel extends RepresentationModel<QuizModel> {
    private QuizDto quizDto;
}
