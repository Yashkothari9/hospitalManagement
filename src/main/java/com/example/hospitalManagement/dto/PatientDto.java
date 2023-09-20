package com.example.hospitalManagement.dto;

import com.example.hospitalManagement.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PatientDto {
    private String name;
    private Integer age;
    private Integer roomNo;
    private String doctorName;
    private Date admitDate;
    private Integer expense;
    private Status status;
}
