package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.entity.Organization;

import java.util.List;
@Service
public interface OrganizationService {

    public List<Organization> getOrganizations() throws Exception;
    
} 
