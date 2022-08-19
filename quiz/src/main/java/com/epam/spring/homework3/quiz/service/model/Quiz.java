package com.epam.spring.homework3.quiz.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "quiz")
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "quiz_type", nullable = false)
    private String quizType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Question> questionList = new ArrayList<>();

}
