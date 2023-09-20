package com.example.hospitalManagement.service;

import com.example.hospitalManagement.dto.PatientDto;
import com.example.hospitalManagement.dto.PatientUpdateDto;
import com.example.hospitalManagement.entities.Patient;
import com.example.hospitalManagement.enums.Status;
import com.example.hospitalManagement.exception.PatientException;

import java.util.List;

public interface PatientService {
    public Patient addPatient(PatientDto patient,String staffEmailId) throws Exception;
    public Patient updatePatient(Patient patient, String staffEmailId) throws Exception;

    public List<Patient> fetchAllAdmittedPatientByStatus(Status status,String staffEmailId) throws Exception;

    public Patient dischargePatient(Long patientId,String staffEmailId) throws Exception;
    public Patient getPatient(Long patientId,String staffEmailId) throws Exception;

}
