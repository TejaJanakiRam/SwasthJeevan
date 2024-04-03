package com.example.backend.dto;

import java.sql.Date;

import com.example.backend.entity.USER_GENDER;
// import com.example.backend.entity.USER_TYPE;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientDTO {
    private Date dob;
    private USER_GENDER gender = USER_GENDER.OTHER;
    private String address;
    
}
