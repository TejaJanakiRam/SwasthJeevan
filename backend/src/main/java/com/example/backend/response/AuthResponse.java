package com.example.backend.response;

import com.example.backend.entity.USER_TYPE;

import lombok.Data;


@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_TYPE type;
}
