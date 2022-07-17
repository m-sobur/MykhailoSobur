package com.epam.spring.homework3.quiz.service.model;

import lombok.Builder;
import lombok.Data;

public enum QuestionType {
    TRUE_FALSE_QUESTION,
    SET_ORDER_QUESTION,
    ONE_CORRECT_ANSWER_QUESTION,
    CHECKBOX_QUESTION,
    SET_MAPPING_QUESTION
}
