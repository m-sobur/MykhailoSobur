package com.epam.spring.homework3.quiz.controller.dto;

import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.quiz.validation.UAPhone;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @Null(message = "id should be absent in request", groups = OnUpdate.class)
    @NotNull(message = "id shouldn't be empty", groups = OnCreate.class)
    private Integer id;

    @NotBlank(message = "firstName shouldn't be empty", groups = OnCreate.class)
    private String firstName;

    @NotBlank(message = "lastName shouldn't be empty", groups = OnCreate.class)
    private String lastName;

    @Email
    @Null(message = "email should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "email shouldn't be empty", groups = OnCreate.class)
    private String email;

    @Null(message = "passwd should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "passwd shouldn't be empty", groups = OnCreate.class)
    private String passwd;

    @Null(message = "email should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "email shouldn't be empty", groups = OnCreate.class)
    private String repeatPasswd;

    @Null(message = "userRole should be absent in request", groups = OnUpdate.class)
    @NotNull(message = "userRole shouldn't be empty", groups = OnCreate.class)
    private Integer userRole;

    @UAPhone
    @NotBlank(message = "phoneNumber shouldn't be empty", groups = OnCreate.class)
    private String phoneNumber;
}
