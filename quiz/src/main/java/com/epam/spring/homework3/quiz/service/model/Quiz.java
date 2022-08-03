package com.epam.spring.homework3.quiz.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @Column(name = "title")
    private String title;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "quiz_type")
    private String quizType;

    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz")
    @JsonIgnore
    @ToString.Exclude
    private List<Question> questionList = new ArrayList<>();

    public static class QuizBuilder {
        @Override
        public String toString() {
            return "";
        }
    }
}
