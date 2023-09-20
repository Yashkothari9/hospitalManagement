package com.example.hospitalManagement.service.impl;

import com.example.hospitalManagement.dto.StaffDto;
import com.example.hospitalManagement.entities.Staff;
import com.example.hospitalManagement.exception.StaffException;
import com.example.hospitalManagement.repository.StaffRepository;
import com.example.hospitalManagement.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffServiceImpl implements StaffService {
    private Staff loggedInStaff = null;

    private final StaffRepository staffRepository;

    public Staff loginStaff(StaffDto staffDto) throws StaffException {
        validateStaffData(staffDto);
        Staff staff = verifyStaff(staffDto.getEmailId());

        if(staff==null){
            throw new StaffException("Staff not present , please SignUp then logIn!");
        }
        if(staff.getEnabled().equals(false)){
            throw new StaffException("Staff has been disabled, hence won't be allowed to login!");
        }
        if(!staff.getPassword().equals(staffDto.getPassword())){
            throw new StaffException("Please enter correct Password");
        }
        loggedInStaff=staff;

        System.out.println("Successfully logged in  staff with user id" + loggedInStaff.getId()+"\n");

        return loggedInStaff;
    }

    @Override
    public Boolean validateStaffLoggedIn(String emailId) throws Exception{
        if(loggedInStaff==null){
            throw new StaffException("No Staff Logged in");
        }
        if(loggedInStaff.getEmailId().equals(emailId)){
            return true;
        }
        return false;
    }

    @Override
    public Staff deleteStaff(String emailId) throws Exception {
        Staff staff = verifyStaff(emailId);
        if(staff==null){
            throw new StaffException("Staff not present for given emailId");
        }
        staff.setEnabled(false);
        return staffRepository.save(staff);
    }


    @Override
    public Staff signUpStaff(StaffDto staffDto) throws Exception{
        validateStaffData(staffDto);
        Staff st= verifyStaff(staffDto.getEmailId());
        if(st!=null){
            throw new StaffException("Staff already exists!");
        }
        Staff s = Staff.builder().emailId(staffDto.getEmailId()).password(staffDto.getPassword()).enabled(true).build();
        return staffRepository.save(s);
    }

    @Override
    public Staff deleteStaff(Long id) throws StaffException {
        Optional<Staff> staff = staffRepository.findById(id);
        if(staff.isEmpty()){
            throw new StaffException("Staff not present !");
        }
        staff.get().setEnabled(false);

        return staffRepository.save(staff.get());
    }

    private Staff verifyStaff(String emailId){

        List<Staff> staffList = staffRepository.findAll();
        List<Staff> s = staffList.stream().filter(st -> st.getEmailId().equals(emailId)).toList();
        if(s.size()==1){
            return s.get(0);
        }

        return null;
    }

    private void validateStaffData(StaffDto s) throws StaffException {
        if(s==null){
            throw new StaffException("Staff data is null");
        }
        if(s.getEmailId()==null){
            throw new StaffException("Email Id not present!");
        }
        if(s.getPassword()==null){
            throw new StaffException("Password in not present");
        }
        if(s.getPassword().length()<4){
            throw new StaffException("Please enter a password with lenth >4");
        }

    }
}
