package com.plukash.testuserservice.utilities.CustomExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User with such credentials not found!");
    }
}
