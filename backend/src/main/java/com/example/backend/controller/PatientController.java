package com.example.backend.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Patient;
import com.example.backend.entity.User;
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

    @PatchMapping("/update")
    public ResponseEntity<Patient> updatePatientDetails(@RequestHeader("Authorization") String jwt, @RequestBody Map<String, Object> updatedData) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Patient patient = patientService.getPatientByUsername(user.getUsername());  
        return (new ResponseEntity<>(patientService.updatePatient(patient, updatedData), HttpStatus.OK));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Patient> deletePatient(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        patientService.deletePatientByID(user.getId());
        return (new ResponseEntity<>(null,HttpStatus.OK));
    }

}
