package com.example.backend.dto;

import com.example.backend.entity.USER_TYPE;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private USER_TYPE type = USER_TYPE.PATIENT;
    private String email;
    private String phone;
    private String name;

}
