package com.example.backend.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Doctor;
import com.example.backend.entity.USER_GENDER;
import com.example.backend.entity.User;
import com.example.backend.service.DoctorService;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private UserService userService;

    @Autowired 
    private DoctorService doctorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public ResponseEntity<Doctor> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Doctor doctor = doctorService.getDoctorbyUsername(user.getUsername());
        return (new ResponseEntity<>(doctor, HttpStatus.OK));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestHeader("Authorization") String jwt) throws Exception {
        List<Doctor> doctorsList = doctorService.getAllDoctors();
        return(new ResponseEntity<>(doctorsList, HttpStatus.OK));
    }

    @PatchMapping("/update")
    public ResponseEntity<Doctor> updateDoctorDetails(@RequestHeader("Authorization") String jwt, @RequestBody Map<String, Object> updatedData) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Doctor doctor = doctorService.getDoctorbyUsername(user.getUsername());  
    
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
        if(updatedData.containsKey("password")){
            String password = (String)updatedData.get("password");
            updatedData.put("password",passwordEncoder.encode(password));
            // System.out.println("Changed password of doctor");
        }
        // System.out.println("Updated doctor");

        return (new ResponseEntity<>(doctorService.updateDoctor(doctor, updatedData), HttpStatus.OK));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Doctor> deleteDoctor(@RequestHeader("Authorization") String jwt, @RequestBody Map<String, Long> requestBody) throws Exception{
        doctorService.deleteDoctorById(requestBody.get("doctorId"));
        System.out.println("Deleted doctor with ID:"+requestBody.get("doctorId"));  
        return (new ResponseEntity<>(null,HttpStatus.OK));
    }
}
