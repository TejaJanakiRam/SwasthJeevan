package com.example.backend.dto;

import java.sql.Date;

import com.example.backend.entity.Organization;
import com.example.backend.entity.Speciality;
import com.example.backend.entity.USER_GENDER;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DoctorDTO extends UserDTO {
    // private Long orgId;
    // private Long specId;
    private Organization organization;
    private Speciality speciality;
   
    // private Date dob;
    private USER_GENDER gender = USER_GENDER.OTHER;
    // private String address;
    private String registrationNo;
     
}
