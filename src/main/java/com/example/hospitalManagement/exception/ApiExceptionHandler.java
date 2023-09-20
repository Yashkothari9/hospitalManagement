package com.example.hospitalManagement.exception;

import com.example.hospitalManagement.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.ServerException;

import static com.example.hospitalManagement.responseBuilders.ResponseBuilder.buildErrorResponse;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {PatientException.class})
    public ResponseEntity<ErrorResponse> handlePatientException(PatientException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = {StaffException.class})
    public ResponseEntity<ErrorResponse> handleStaffException(StaffException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = {ServerException.class})
    public ResponseEntity<ErrorResponse> handleServerException(ServerException e) {
        return buildErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
