package com.epam.spring.homework3.quiz.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer_variant")
public class AnswerVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "variant_title", nullable = false)
    private String variantTitle;

    @Transient
    private Boolean userChecked;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(name = "answer_variant_question_fk"))
    @ToString.Exclude
    @JsonIgnore
    private Question question;

    @Column(name = "value", nullable = false)
    private Boolean value;

}

