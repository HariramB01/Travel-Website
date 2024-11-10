package com.travel.UserService.Exception;

public class PasswordAlreadyExistsException extends RuntimeException {
    public PasswordAlreadyExistsException(String message) {
        super(message);
    }
}
