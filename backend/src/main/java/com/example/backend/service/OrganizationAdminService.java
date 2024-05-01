package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entity.OrganizationAdmin;
import com.example.backend.repository.OrganizationAdminRepository;

@Service
public class OrganizationAdminService {
    @Autowired
    private OrganizationAdminRepository organizationAdminRepository;

    public OrganizationAdmin getOrgAdminByUsername(String username){
        return(organizationAdminRepository.findByUsername(username));
    }

    public List<OrganizationAdmin> getAllOrgAdmins(){
        return(organizationAdminRepository.findAll());
    }


}
