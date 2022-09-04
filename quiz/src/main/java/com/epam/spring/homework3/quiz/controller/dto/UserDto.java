package com.epam.spring.homework3.quiz.controller.dto;

import com.epam.spring.homework3.quiz.controller.dto.group.OnCreate;
import com.epam.spring.homework3.quiz.controller.dto.group.OnUpdate;
import com.epam.spring.homework3.quiz.validation.RepeatPasswordEqualToPassword;
import com.epam.spring.homework3.quiz.validation.UAPhone;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@RepeatPasswordEqualToPassword(message = "{user.password.equals.exception}", groups = OnCreate.class)
public class UserDto {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotBlank(message = "{user.firstName.empty.exception}", groups = OnCreate.class)
    private String firstName;

    @NotBlank(message = "{user.lastName.empty.exception}", groups = OnCreate.class)
    private String lastName;

    @Email(message = "{user.email.template.exception}")
    @Null(message = "{user.email.absent.exception}", groups = OnUpdate.class)
    @NotBlank(message = "{user.email.empty.exception}", groups = OnCreate.class)
    private String email;

    @Null(message = "{user.password.absent.exception}", groups = OnUpdate.class)
    @NotBlank(message = "{user.password.empty.exception}", groups = OnCreate.class)
    private String passwd;

    @Null(message = "{user.repeatPassword.absent.exception}", groups = OnUpdate.class)
    @NotBlank(message = "{user.repeatPassword.empty.exception}", groups = OnCreate.class)
    private String repeatPasswd;

    @Null(message = "{user.userRole.absent.exception}", groups = OnUpdate.class)
    @NotNull(message = "{user.userRole.empty.exception}", groups = OnCreate.class)
    private Integer userRole;

    @UAPhone(message = "{user.phoneNumber.template.exception}")
    @NotBlank(message = "{user.phoneNumber.empty.exception}", groups = OnCreate.class)
    private String phoneNumber;
}
