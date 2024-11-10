package com.travel.UserService.Exception;

public class MobileNoAlreadyInUseException extends RuntimeException {
    public MobileNoAlreadyInUseException(String message) {
        super(message);
    }
}
