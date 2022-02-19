package com.epam.spring.homework3.quiz.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    // The fields are named in the same as in the database
    private Long id_usr;
    private String first_name;
    private String last_name;
    private String email;
    private String passwd;
    private String repeatPasswd;
    private Integer usr_role;
}
