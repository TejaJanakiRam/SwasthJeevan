package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.Organization;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    // custom find method
    // name function according to field name
    public Organization findByRegistrationNum(String registrationNum);
}
