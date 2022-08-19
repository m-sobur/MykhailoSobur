package com.epam.spring.homework3.quiz.controller.assembler;

import com.epam.spring.homework3.quiz.controller.QuestionController;
import com.epam.spring.homework3.quiz.controller.assembler.model.QuestionModel;
import com.epam.spring.homework3.quiz.controller.dto.QuestionDto;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuestionAssembler extends RepresentationModelAssemblerSupport<QuestionDto, QuestionModel> {
    public static final String GET_REL = "Get question by id";
    public static final String CREATE_REL = "Create question";
    public static final String UPDATE_REL = "Update question by id";
    public static final String DELETE_REL = "Delete question by id";
    public static final String GET_ALL_REL = "Get all question by parent id";

    public QuestionAssembler() {
        super(QuestionController.class, QuestionModel.class);
    }

    @Override
    @NonNull
    public QuestionModel toModel(@NonNull QuestionDto entity) {
        QuestionModel questionModel = new QuestionModel(entity);

        Link get = linkTo(methodOn(QuestionController.class).getQuestionByID(entity.getId())).withRel(GET_REL);
        Link create = linkTo(methodOn(QuestionController.class).createQuestion(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(QuestionController.class).updateQuestionById(entity.getId(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(QuestionController.class).deleteQuestionById(entity.getId())).withRel(DELETE_REL);
        Link getAll = linkTo(methodOn(QuestionController.class).getAllQuestionsByParentQuizId(entity.getQuizId(), Pageable.unpaged())).withRel(GET_ALL_REL);


        questionModel.add(get, create, update, delete, getAll);

        return questionModel;
    }
}
