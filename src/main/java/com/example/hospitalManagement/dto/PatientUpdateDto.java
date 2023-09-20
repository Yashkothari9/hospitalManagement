package com.example.hospitalManagement.dto;

import com.example.hospitalManagement.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PatientUpdateDto {
    private Long id;
    private String name;
    private Integer age;
    private Integer roomNo;
    private String doctorName;
    private String admitDate;
    private Integer expense;
    private Status status;
}
