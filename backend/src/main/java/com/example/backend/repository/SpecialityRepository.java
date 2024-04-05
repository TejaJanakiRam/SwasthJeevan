package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Speciality;


@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    // custom find method
    // name function according to field name
    public Speciality findBySpecialityCode(String specialityCode);
}
