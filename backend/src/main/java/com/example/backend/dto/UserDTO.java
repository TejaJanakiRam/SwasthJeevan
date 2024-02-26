package com.example.backend.dto;

import com.example.backend.entity.USER_ROLE;

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
    private USER_ROLE role = USER_ROLE.PATIENT;
}
