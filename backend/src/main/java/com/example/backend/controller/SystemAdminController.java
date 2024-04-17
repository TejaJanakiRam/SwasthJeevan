package com.example.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.entity.Organization;
import com.example.backend.entity.SystemAdmin;
import com.example.backend.entity.User;
import com.example.backend.mapper.OrganizationAdminMapper;
import com.example.backend.mapper.OrganizationMapper;
import com.example.backend.repository.OrganizationRepository;
import com.example.backend.repository.SystemAdminRepository;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/api/sys_admin")
public class SystemAdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @PostMapping("/register_organization")
    public ResponseEntity<Organization> registerOrganization(@RequestHeader("Authorization") String jwt,
            @RequestBody Map<String, Object> request) throws Exception {

        Organization organization = organizationMapper.mapToOrganization(request);
        Organization organizationexist = organizationRepository
                .findByRegistrationNum(organization.getRegistrationNum());
        if (organizationexist != null) {
            throw new Exception("Organization exists");
        }
        Organization savedOrganization = organizationRepository.save(organization);
        return (new ResponseEntity<>(savedOrganization, HttpStatus.CREATED));
    }

}
