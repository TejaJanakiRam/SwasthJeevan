package com.example.backend.mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.backend.repository.OrganizationRepository;
import com.example.backend.entity.Organization;
import com.example.backend.entity.OrganizationAdmin;
import com.example.backend.entity.Patient;
import com.example.backend.entity.USER_GENDER;
import com.example.backend.entity.USER_TYPE;

@Component
public class OrganizationAdminMapper {
    @Autowired
    private OrganizationRepository organizationRepository;

    public OrganizationAdmin mapToOrganization(Map<String, Object> requestBody) throws Exception {
        OrganizationAdmin organizationAdmin = new OrganizationAdmin();
        organizationAdmin.setUsername((String) requestBody.get("username"));
        organizationAdmin.setPassword((String) requestBody.get("password"));
        organizationAdmin.setType(USER_TYPE.valueOf((String) requestBody.get("type")));
        organizationAdmin.setEmail((String) requestBody.get("email"));
        organizationAdmin.setPhone((String) requestBody.get("phone"));
        organizationAdmin.setName((String) requestBody.get("name"));
        Organization organization = organizationRepository
                .findByRegistrationNum((String) requestBody.get("org_registration_num"));
        if (organization == null) {
            throw new Exception("Organization not there");
        }
        organizationAdmin.setOrganization(organization);

        return (organizationAdmin);
    }

}
