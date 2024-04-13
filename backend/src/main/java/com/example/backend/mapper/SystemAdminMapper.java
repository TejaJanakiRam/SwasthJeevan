package com.example.backend.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.backend.entity.SystemAdmin;
import com.example.backend.entity.USER_TYPE;

@Component
public class SystemAdminMapper {
    public SystemAdmin mapToSystemAdmin(Map<String, Object> requestBody){
        SystemAdmin systemAdmin = new SystemAdmin();
        systemAdmin.setUsername((String) requestBody.get("username"));
        systemAdmin.setPassword((String) requestBody.get("password"));
        systemAdmin.setType(USER_TYPE.valueOf((String) requestBody.get("type")));
        systemAdmin.setEmail((String) requestBody.get("email"));
        systemAdmin.setPhone((String) requestBody.get("phone"));
        systemAdmin.setName((String) requestBody.get("name"));
        return (systemAdmin);
    }
}
