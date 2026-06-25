package com.coditas.thresholdclinicproject.exceptions;

public class UnAuthenticatedUserException extends RuntimeException {
    public UnAuthenticatedUserException(String message) {
        super(message);
    }
}
