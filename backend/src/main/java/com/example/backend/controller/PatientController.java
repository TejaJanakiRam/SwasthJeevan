package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.PatientDTO;
import com.example.backend.entity.Patient;
import com.example.backend.entity.User;
import com.example.backend.mapper.PatientMapper;
import com.example.backend.repository.PatientRepository;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private UserService userService;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return (new ResponseEntity<>(user, HttpStatus.OK));
    }
    
}
