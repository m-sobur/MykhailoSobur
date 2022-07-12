package com.epam.spring.homework3.quiz.service.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwd;
    private Integer userRole;
}
