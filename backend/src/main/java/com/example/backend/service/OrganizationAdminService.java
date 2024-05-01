package com.example.backend.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.backend.entity.OrganizationAdmin;
import com.example.backend.entity.Patient;
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

    public OrganizationAdmin updateOrgAdm(OrganizationAdmin orgadm,Map<String, Object>newData){
        newData.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Patient.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, orgadm, value);
        });
        organizationAdminRepository.save(orgadm);
        return orgadm;
    }

    public void deleteOrgAdmById(Long orgadm_id){
        organizationAdminRepository.deleteById(orgadm_id);
    }
}
