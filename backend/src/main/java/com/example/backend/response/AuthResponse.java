package com.example.backend.response;

import com.example.backend.entity.USER_ROLE;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
