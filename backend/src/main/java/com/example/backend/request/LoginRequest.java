package com.example.backend.request;

import com.example.backend.entity.USER_TYPE;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private USER_TYPE type;
}
