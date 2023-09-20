package com.example.hospitalManagement.controllers;

import com.example.hospitalManagement.dto.StaffDto;
import com.example.hospitalManagement.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.hospitalManagement.responseBuilders.ResponseBuilder.buildErrorResponse;
import static com.example.hospitalManagement.responseBuilders.ResponseBuilder.buildResponse;

@RestController
@RequestMapping("/api/hms/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/signUp")
    public ResponseEntity<?> addStaff(@RequestBody StaffDto staffDto) throws Exception {
        try {
            return buildResponse(staffService.signUpStaff(staffDto), "Added staff");
        }catch (Exception e){
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStaff(@RequestBody StaffDto staffDto) throws Exception {
        try {
            return buildResponse(staffService.loginStaff(staffDto), "Logged in staff");
        }catch (Exception e){
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteStaff(@RequestBody String email) throws Exception {
        try {
            return buildResponse(staffService.deleteStaff(email), "Logged in staff");
        }catch (Exception e){
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

}
