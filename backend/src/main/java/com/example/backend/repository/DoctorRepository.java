package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {  
    public Doctor findByUsername(String username);
} 
