package com.epam.spring.homework3.quiz.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwd;
    private String repeatPasswd;
    private Integer userRole;
}