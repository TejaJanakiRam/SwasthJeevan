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

    public PatientDTO(Long id, String username, String password, USER_TYPE type, String email, String phone, String name, Date dob, USER_GENDER gender, String address) {
        super(id, username, password, type, email, phone, name);
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }
    
}
