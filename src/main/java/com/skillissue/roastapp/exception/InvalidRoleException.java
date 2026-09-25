package com.skillissue.roastapp.exception;

public class InvalidRoleException extends RuntimeException {

    public InvalidRoleException() {
        super("Invalid role");
    }

    public InvalidRoleException(String message) {
        super(message);
    }

    public InvalidRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRoleException(Throwable cause) {
        super(cause);
    }
}
