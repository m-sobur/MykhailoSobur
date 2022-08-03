package com.epam.spring.homework3.quiz.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @Column(name = "variant_title")
    private String variantTitle;

    @Transient
    private Boolean userChecked;

    @ManyToOne(optional = false)
    @ToString.Exclude
    @JsonIgnore
    private Question question;

    @Column(name = "value")
    private Boolean value;

    public static class AnswerVariantBuilder {
        @Override
        public String toString() {
            return "";
        }
    }
}

