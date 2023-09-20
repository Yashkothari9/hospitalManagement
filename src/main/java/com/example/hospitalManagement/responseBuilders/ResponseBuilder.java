package com.example.hospitalManagement.responseBuilders;

import com.example.hospitalManagement.responses.ErrorResponse;
import com.example.hospitalManagement.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static ResponseEntity<Response> buildResponse(Object data, String message) {
        Response response = Response.builder().data(data).message(message).status(HttpStatus.OK).build();
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<ErrorResponse> buildErrorResponse(String message, Integer errorCode) {
        ErrorResponse errorResponse = ErrorResponse.builder().message(message)
                .status(HttpStatus.resolve(errorCode))
                .build();
        return ResponseEntity.status(errorCode).body(errorResponse);
    }
}