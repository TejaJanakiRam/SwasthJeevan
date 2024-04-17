package com.example.backend.service.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entity.Organization;
import com.example.backend.repository.OrganizationRepository;
import com.example.backend.service.OrganizationService;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    OrganizationRepository organizationRepository;
    
    @Override
    public List<Organization> getOrganizations() throws Exception {
        return(organizationRepository.findAll());
    }
    
}