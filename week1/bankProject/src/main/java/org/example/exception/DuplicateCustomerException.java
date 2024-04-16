package org.example.exception;

public class DuplicateCustomerException extends Exception {
    public DuplicateCustomerException(String message) {
        super(message);
    }
}
