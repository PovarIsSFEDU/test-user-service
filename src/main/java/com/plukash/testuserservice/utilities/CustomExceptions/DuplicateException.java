package com.plukash.testuserservice.utilities.CustomExceptions;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String msg) {
        super(msg);
    }
}
