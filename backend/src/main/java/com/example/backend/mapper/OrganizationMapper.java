package com.example.backend.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.backend.dto.OrganizationDTO;
import com.example.backend.entity.Organization;

@Component
public class OrganizationMapper {
    // public static Organization mapToOrganization(OrganizationDTO organizationDTO) {
    //     Organization organization = new Organization(organizationDTO.getId(), organizationDTO.getName(),organizationDTO.getRegistration_number(),organizationDTO.getPhone(), organizationDTO.getEmail() ,organizationDTO.getAddress() ); 
    //     return (organization);
    // }
    // public static OrganizationDTO mapToOrganizationDTO(Organization Organization){
    //     OrganizationDTO OrganizationDTO = new OrganizationDTO(Organization.getId(), Organization.getName(), Organization.getRegistration_number(), Organization.getPhone(),Organization.getEmail(), Organization.getAddress());
    //     return(OrganizationDTO);
    // }
    public Organization mapToOrganization(Map<String, Object> requestBody) {
        Organization organization = new Organization();
        organization.setName((String) requestBody.get("name"));
        organization.setRegistrationNum((String) requestBody.get("registrationNum"));
        organization.setPhone((String) requestBody.get("phone"));
        organization.setEmail((String) requestBody.get("email"));
        organization.setAddress((String) requestBody.get("address"));
        return (organization);
    }
}
