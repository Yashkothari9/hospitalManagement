package com.example.hospitalManagement.controllers;

import com.example.hospitalManagement.dto.PatientDto;
import com.example.hospitalManagement.entities.Patient;
import com.example.hospitalManagement.enums.Status;
import com.example.hospitalManagement.responses.ErrorResponse;
import com.example.hospitalManagement.service.impl.PatientServiceImpl;
import com.example.hospitalManagement.service.impl.StaffServiceImpl;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.example.hospitalManagement.responseBuilders.ResponseBuilder.buildErrorResponse;
import static com.example.hospitalManagement.responseBuilders.ResponseBuilder.buildResponse;

@RestController
@RequestMapping("/api/hms/patient")
@Slf4j
public class PatientController {

    @Autowired
    private StaffServiceImpl staffService;

    @Autowired
    private PatientServiceImpl patientService ;

    @PostMapping("/add")
    public ResponseEntity<?> addPatient(@RequestBody PatientDto patientDto, @RequestHeader("staffEmailId") String staffEmail) throws Exception {
        try {
//            String staffEmail = Objects.requireNonNull(httpHeaders.get("staffEmailId")).toString();
            log.info("staffEmail: {} ", staffEmail);
            return buildResponse(patientService.addPatient(patientDto, staffEmail), "Patient added successFully");
        }catch (Exception e){
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }

    }

    @PostMapping("/update")
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient, @RequestHeader("staffEmailId") String staffEmail) throws Exception {
        try {
            return buildResponse(patientService.updatePatient(patient, staffEmail), "Patient update successFully");
        }catch (Exception e){
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/discharge/{patientId}")
    public ResponseEntity<?> dischargePatient(@PathVariable("patientId") Long patientId, @RequestHeader("staffEmailId") String staffEmail) throws Exception {
        try {
            log.info("PatientId:{}",patientId);
            return buildResponse(patientService.dischargePatient(patientId, staffEmail), "Patient discharged successFully");
        } catch (Exception e){
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @GetMapping("/admitted")
    public ResponseEntity<?> getAdmittedPatient( @RequestHeader("staffEmailId") String staffEmail) throws Exception {
        try {
            return buildResponse(patientService.fetchAllAdmittedPatientByStatus(Status.ADMITTED, staffEmail), "Success");
        }catch (Exception e){
            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

}

