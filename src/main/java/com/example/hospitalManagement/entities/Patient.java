package com.example.hospitalManagement.entities;

import com.example.hospitalManagement.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Integer roomNo;
    private String doctorName;
    private Date admitDate;
    private Integer expense;
    private Status status;
    private String createdBy;
    private String modifiedBy;

}
