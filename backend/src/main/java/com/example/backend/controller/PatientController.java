package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Patient;
import com.example.backend.entity.User;
import com.example.backend.repository.PatientRepository;
import com.example.backend.service.PatientService;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/profile")
    public ResponseEntity<Patient> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Patient patient = patientService.getPatientByUsername(user.getUsername());
        return (new ResponseEntity<>(patient, HttpStatus.OK));
    }
    
}
