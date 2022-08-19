package com.epam.spring.homework3.quiz.controller.assembler;

import com.epam.spring.homework3.quiz.controller.QuizController;
import com.epam.spring.homework3.quiz.controller.assembler.model.QuizModel;
import com.epam.spring.homework3.quiz.controller.dto.QuizDto;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuizAssembler extends RepresentationModelAssemblerSupport<QuizDto, QuizModel> {
    public static final String GET_REL = "Get quiz by title";
    public static final String CREATE_REL = "Create quiz";
    public static final String UPDATE_REL = "Update quiz by title";
    public static final String DELETE_REL = "Delete quiz by title";
    public static final String GET_ALL_BY_ID_REL = "Get all quiz by creator id";
    public static final String GET_ALL_REL = "Get all quiz by creator id";

    public QuizAssembler() {
        super(QuizController.class, QuizModel.class);
    }

    @Override
    @NonNull
    public QuizModel toModel(@NonNull QuizDto entity) {
        QuizModel quizModel = new QuizModel(entity);

        Link get = linkTo(methodOn(QuizController.class).getQuizByTitle(entity.getTitle())).withRel(GET_REL);
        Link create = linkTo(methodOn(QuizController.class).createQuiz(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(QuizController.class).updateQuizByTitle(entity.getTitle(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(QuizController.class).deleteQuizByTitle(entity.getTitle())).withRel(DELETE_REL);
        Link getAllById = linkTo(methodOn(QuizController.class).getAllQuizesByCreatorId(entity.getCreatorId(), Pageable.unpaged())).withRel(GET_ALL_BY_ID_REL);


        quizModel.add(get, create, update, delete, getAllById);

        return quizModel;
    }
}
