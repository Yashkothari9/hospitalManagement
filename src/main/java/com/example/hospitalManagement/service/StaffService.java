package com.example.hospitalManagement.service;

import com.example.hospitalManagement.dto.StaffDto;
import com.example.hospitalManagement.entities.Staff;
import com.example.hospitalManagement.exception.StaffException;

public interface StaffService {
    public Staff signUpStaff(StaffDto staffDto) throws Exception;
    public Staff deleteStaff(Long id) throws StaffException;
    public Staff loginStaff(StaffDto staffDto) throws StaffException;
    public Boolean validateStaffLoggedIn(String emailId) throws Exception;

    public Staff deleteStaff(String emailId) throws Exception;
}
