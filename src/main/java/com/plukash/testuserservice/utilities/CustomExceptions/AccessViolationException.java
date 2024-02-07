package com.plukash.testuserservice.utilities.CustomExceptions;

public class AccessViolationException extends RuntimeException {
    public AccessViolationException(String message) {
        super(message);
    }
}
