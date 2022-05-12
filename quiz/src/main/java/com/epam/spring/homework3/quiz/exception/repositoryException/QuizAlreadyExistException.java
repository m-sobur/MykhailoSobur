package com.epam.spring.homework3.quiz.exception.repositoryException;

public class QuizAlreadyExistException extends RepositoryException{
    public QuizAlreadyExistException() {
    }

    public QuizAlreadyExistException(String message) {
        super(message);
    }

    public QuizAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuizAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public QuizAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
