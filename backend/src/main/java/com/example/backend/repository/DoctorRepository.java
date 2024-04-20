package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Doctor;
import com.example.backend.entity.Speciality;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {  
    public Doctor findByUsername(String username);
    // public Doctor findById(Long id);
    public List<Doctor> findBySpeciality(Speciality speciality);
} 
