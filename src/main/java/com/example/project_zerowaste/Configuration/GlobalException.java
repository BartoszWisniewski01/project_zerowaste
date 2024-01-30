package com.example.project_zerowaste.Configuration;

public class GlobalException extends RuntimeException {
    public GlobalException() {
        super("There is an error.");
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }
}
