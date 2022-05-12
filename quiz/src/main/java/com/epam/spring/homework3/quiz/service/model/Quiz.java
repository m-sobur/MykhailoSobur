package com.epam.spring.homework3.quiz.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Quiz {
    // The fields are named in the same as in the database
    private UUID id_quiz;
    private String title;
    private Date creationDate;
    private UUID creator;
    private String quiz_type;

}
