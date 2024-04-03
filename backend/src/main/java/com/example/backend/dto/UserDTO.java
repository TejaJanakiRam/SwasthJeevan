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
    
    // @Column(length =255) 
    private String username;

    // @Column(length =255) 
    private String password;

    private USER_TYPE type = USER_TYPE.PATIENT;

    // @Column(length =255) 
    private String email;

    // @Column(length =10) 
    private String phone;
    private String name;

}
