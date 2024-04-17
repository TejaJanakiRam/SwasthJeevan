package com.example.backend.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entity.Speciality;
import com.example.backend.repository.SpecialityRepository;
import com.example.backend.service.SpecialityService;
import java.util.List;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public List<Speciality> getSpecialities() throws Exception {
        return(specialityRepository.findAll());
    }

}
