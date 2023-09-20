package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.entities.Patient;
import com.example.hospitalManagement.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByStatus(Status s);
}
