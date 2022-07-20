package com.epam.spring.homework3.quiz.exception.repository;

public class ElementAlreadyExistException extends RepositoryException{
    public ElementAlreadyExistException(String message) {
        super(message);
    }
}
