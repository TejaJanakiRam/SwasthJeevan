package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.entity.Speciality;
import java.util.*;

@Service
public interface SpecialityService {

    public List<Speciality> getSpecialities() throws Exception;
    
} 