package com.example.hospitalManagement.exception;

public class StaffException extends Exception{
    public StaffException(String message) {
        super(message);
    }

    public StaffException(String message, Throwable cause) {
        super(message, cause);
    }
}
