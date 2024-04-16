package org.example.exception;

public class InvalidAccountOperationException extends Exception {
    public InvalidAccountOperationException(String message) {
        super(message);
    }
}
