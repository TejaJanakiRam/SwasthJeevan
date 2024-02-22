package com.backend.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.dto.UserDto;
import com.backend.backend.service.UserService;
// import com.backend.backend.service.impl.UserServiceImpl;

@RestController

@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService usi;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto savedUser = usi.createUser(userDto);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

}
