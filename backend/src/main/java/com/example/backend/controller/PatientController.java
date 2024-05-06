package com.example.backend.controller;

import java.sql.Date;
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
import com.example.backend.entity.USER_GENDER;
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
        // System.out.println("Checkpoint 1");
        User user = userService.findUserByJwtToken(jwt);
        Patient patient = patientService.getPatientByUsername(user.getUsername());  
        // System.out.println("Updated patient with ID: "+user.getId());
        if (updatedData.containsKey("dob")) {
            // Convert the dob from String to java.sql.Date
            String dobString = (String) updatedData.get("dob");
            Date dob = Date.valueOf(dobString);
            updatedData.put("dob", dob);
        }
        if (updatedData.containsKey("gender")) {
            String genderString = (String) updatedData.get("gender");
            // Convert the string value to USER_GENDER enum type
            USER_GENDER genderEnum;
            switch (genderString.toUpperCase()) {
                case "MALE":
                    genderEnum = USER_GENDER.MALE; 
                    break;
                case "FEMALE":
                    genderEnum = USER_GENDER.FEMALE;
                    break;
                case "OTHER":
                    genderEnum = USER_GENDER.OTHER;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown gender: " + genderString);
            }
            updatedData.put("gender", genderEnum);
        }
        return (new ResponseEntity<>(patientService.updatePatient(patient, updatedData), HttpStatus.OK));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Patient> deletePatient(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        patientService.deletePatientByID(user.getId());
        System.out.println("Deleted patient with ID: "+user.getId());
        return (new ResponseEntity<>(null,HttpStatus.OK));
    }

}
