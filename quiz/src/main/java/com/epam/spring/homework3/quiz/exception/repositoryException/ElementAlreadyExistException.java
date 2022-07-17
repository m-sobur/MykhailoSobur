package com.epam.spring.homework3.quiz.exception.repositoryException;

public class ElementAlreadyExistException extends RepositoryException{
    public ElementAlreadyExistException(String message) {
        super(message);
    }
}
