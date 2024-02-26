package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.entity.User;

@Service
public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByUsername(String username) throws Exception;
}
