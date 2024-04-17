package com.example.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import com.example.backend.entity.Organization;
import com.example.backend.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/api/organizations")
    public ResponseEntity<List<Organization>> getAllOrganizations(@RequestHeader("Authorization") String jwt)
            throws Exception {
        List<Organization> organizations = organizationService.getOrganizations();
        return (new ResponseEntity<>(organizations, HttpStatus.OK));
    }
}
