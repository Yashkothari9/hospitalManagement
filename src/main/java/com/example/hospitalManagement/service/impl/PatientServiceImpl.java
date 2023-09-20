package com.example.hospitalManagement.service.impl;

import com.example.hospitalManagement.dto.PatientDto;
import com.example.hospitalManagement.entities.Patient;
import com.example.hospitalManagement.enums.Status;
import com.example.hospitalManagement.exception.PatientException;
import com.example.hospitalManagement.exception.StaffException;
import com.example.hospitalManagement.repository.PatientRepository;
import com.example.hospitalManagement.service.PatientService;
import com.example.hospitalManagement.service.StaffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final StaffService staffService;
    @Override
    public Patient addPatient(PatientDto patientDto,String staffEmailId) throws Exception{
        if(staffService.validateStaffLoggedIn(staffEmailId)) {
            validatePatient(patientDto);
            Patient p = Patient.builder()
                    .name(patientDto.getName())
                    .age(patientDto.getAge())
                    .roomNo(patientDto.getRoomNo())
                    .doctorName(patientDto.getDoctorName())
                    .expense(patientDto.getExpense())
                    .status(patientDto.getStatus().equals(Status.ADMITTED)?Status.ADMITTED:Status.DISCHARGED)
                    .admitDate(patientDto.getAdmitDate())
                    .createdBy(staffEmailId)
                    .modifiedBy(staffEmailId).build();

            return patientRepository.save(p);
        }else {
            throw new StaffException("Invalid Staff Data, please Login and then add!");
        }
    }

   @Override
    public Patient updatePatient(Patient patient,String staffEmailId) throws Exception {
        if(staffService.validateStaffLoggedIn(staffEmailId)) {
            Patient patientToUpdate= patientRepository.findById(patient.getId()).orElse(null);
            if(patientToUpdate==null ){
                throw new PatientException("Invalid Patient Data entered for update");
            }
            if(patient.getName()!=null){
                patientToUpdate.setName(patient.getName());
            }
            if(patient.getAge()!=null){
                patientToUpdate.setAge(patient.getAge());
            }
            if(patient.getStatus()!=null){
                patientToUpdate.setStatus(patient.getStatus());
            }
            if(patient.getExpense()!=null){
                patientToUpdate.setExpense(patient.getExpense());
            }
            if(patient.getDoctorName()!=null){
                patientToUpdate.setDoctorName(patient.getDoctorName());
            }
            patientToUpdate.setModifiedBy(staffEmailId);
            return patientRepository.save(patientToUpdate);
        }else {
            throw new StaffException("Invalid Staff Data, please Login and then add!");
        }
    }

    @Override
    public List<Patient> fetchAllAdmittedPatientByStatus(Status status,String staffEmailId) throws Exception {
        log.info("Here!");
        if(!staffService.validateStaffLoggedIn(staffEmailId)){
            throw new StaffException("Invalid staff data, please login/signUp then query");
        }
        List<Patient>  patients= patientRepository.findAll();
        return patients.stream().filter(patient -> patient.getStatus().equals(Status.ADMITTED)).collect(Collectors.toList());
    }

    @Override
    public Patient dischargePatient(Long patientId,String staffEmailId) throws Exception {
        log.info("PatientId:{}",patientId);
        if(staffService.validateStaffLoggedIn(staffEmailId)) {
            Patient p = patientRepository.findById(patientId).orElse(null);
            if (p == null) {
                throw new PatientException("Incorrect patientId entered!");
            }
            if (p.getStatus() == Status.DISCHARGED) {
                throw new PatientException(" Patient is already discharged!");
            }

            p.setStatus(Status.DISCHARGED);
            p.setModifiedBy(staffEmailId);
            return patientRepository.save(p);
        }else {
            throw new StaffException("Staff Not Logged In please LogIn");
        }
    }

    @Override
    public Patient getPatient(Long patientId,String staffEmailId) throws Exception {
        if(staffService.validateStaffLoggedIn(staffEmailId)) {
            Patient p = patientRepository.findById(patientId).orElse(null);
            if (p == null) {
                throw new PatientException("Incorrect patientId entered!");
            }
            return p;
        }else {
            throw new StaffException("Staff Not Logged In please LogIn");
        }
    }

    private Boolean validatePatient(PatientDto patientDto) throws PatientException {
        if(patientDto.getAge()==null){
            throw new PatientException("Patient Age not present");
        }
        if( patientDto.getAge()<0 || patientDto.getAge()>100){
            throw new PatientException("Patient Age incorrect");
        }
        if(patientDto.getName()==null){
            throw new PatientException("Patient Name not present");
        }
        if(patientDto.getStatus()==null){
            throw new PatientException("Patient Status is null, please add a value");
        }
        if(patientDto.getRoomNo()==null){
            throw new PatientException("Patient Room is null, please add a correct value");
        }
        if(patientDto.getDoctorName()==null){
            throw new PatientException("Please enter a valid Doctor Name");
        }
        return true;
    }


}
