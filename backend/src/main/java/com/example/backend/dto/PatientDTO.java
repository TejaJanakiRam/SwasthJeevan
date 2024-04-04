package com.example.backend.dto;

import java.sql.Date;

import com.example.backend.entity.USER_GENDER;
// import com.example.backend.entity.USER_TYPE;
import com.example.backend.entity.USER_TYPE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PatientDTO extends UserDTO {
    private Date dob;
    private USER_GENDER gender = USER_GENDER.OTHER;
    private String address;
    
}
