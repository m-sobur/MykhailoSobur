package com.epam.spring.homework3.quiz.exception.repositoryException;

public class NoSuchQuizException extends RepositoryException{
    public NoSuchQuizException() {
    }

    public NoSuchQuizException(String message) {
        super(message);
    }

    public NoSuchQuizException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchQuizException(Throwable cause) {
        super(cause);
    }

    public NoSuchQuizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
