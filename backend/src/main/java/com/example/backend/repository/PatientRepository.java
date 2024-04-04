package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Patient;
import com.example.backend.entity.User;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Patient findByUsername(String username);
}

