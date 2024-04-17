package com.example.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import com.example.backend.entity.Speciality;
import com.example.backend.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @GetMapping("/api/specialities")
    public ResponseEntity<List<Speciality>> getAllSpecialities(@RequestHeader("Authorization") String jwt)
            throws Exception {
        List<Speciality> specialities = specialityService.getSpecialities();
        return (new ResponseEntity<>(specialities, HttpStatus.OK));
    }
}
