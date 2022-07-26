package com.epam.spring.homework3.quiz.controller.assembler;

import com.epam.spring.homework3.quiz.controller.UserController;
import com.epam.spring.homework3.quiz.controller.assembler.model.UserModel;
import com.epam.spring.homework3.quiz.controller.dto.UserDto;
import lombok.NonNull;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {
    public static final String GET_REL = "Get user by email";
    public static final String CREATE_REL = "Create user";
    public static final String UPDATE_REL = "Update user by email";
    public static final String DELETE_REL = "Delete user by email";

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    @NonNull
    public UserModel toModel(@NonNull UserDto entity) {
        UserModel userModel = new UserModel(entity);

        Link get = linkTo(methodOn(UserController.class).getUserByEmail(entity.getEmail())).withRel(GET_REL);
        Link create = linkTo(methodOn(UserController.class).createUser(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(UserController.class).updateUserByEmail(entity.getEmail(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(UserController.class).deleteUserByEmail(entity.getEmail())).withRel(DELETE_REL);

        userModel.add(get, create, update, delete);

        return userModel;
    }
}
