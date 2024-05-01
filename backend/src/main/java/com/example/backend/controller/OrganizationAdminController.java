package com.example.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.OrganizationAdmin;
import com.example.backend.entity.User;
import com.example.backend.service.OrganizationAdminService;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/api/org_admin")
public class OrganizationAdminController {
    @Autowired
    private UserService userService;

    @Autowired 
    private OrganizationAdminService organizationAdminService;

    @GetMapping("/profile")
    public ResponseEntity<OrganizationAdmin> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        OrganizationAdmin organizationAdmin = organizationAdminService.getOrgAdminByUsername(user.getUsername());
        return (new ResponseEntity<>(organizationAdmin, HttpStatus.OK));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganizationAdmin>> getAlOrgAdmins(@RequestHeader("Authorization") String jwt) throws Exception {
        List<OrganizationAdmin> organizationAdminsList = organizationAdminService.getAllOrgAdmins();
        return(new ResponseEntity<>(organizationAdminsList, HttpStatus.OK));
    }

    @PatchMapping("/update")
    public ResponseEntity<OrganizationAdmin> updateOrgAdmDetails(@RequestHeader("Authorization") String jwt, @RequestBody Map<String, Object> updatedData) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        OrganizationAdmin orgadm = organizationAdminService.getOrgAdminByUsername(user.getUsername());  
        return (new ResponseEntity<>(organizationAdminService.updateOrgAdm(orgadm, updatedData), HttpStatus.OK));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<OrganizationAdmin> deleteOrgAdm(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        organizationAdminService.deleteOrgAdmById(user.getId());  
        return (new ResponseEntity<>(null,HttpStatus.OK));
    }
}
