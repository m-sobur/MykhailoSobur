package com.epam.spring.homework3.quiz.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_title", nullable = false)
    private String questionTitle;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", foreignKey = @ForeignKey(name = "question_quiz_fk"))
    @JsonIgnore
    private Quiz quiz;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<AnswerVariant> answerVariantList = new ArrayList<>();
}
