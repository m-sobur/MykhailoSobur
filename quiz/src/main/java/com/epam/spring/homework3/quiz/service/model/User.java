package com.epam.spring.homework3.quiz.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    // The fields are named in the same as in the database
    private UUID id_usr;
    private String first_name;
    private String last_name;
    private String email;
    private String passwd;
    private Integer usr_role;
}
