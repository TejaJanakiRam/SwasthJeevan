package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)

public class OrganizationDTO {
    private Long id;
    private String name;
    private String registrationNum;
    private String phone;
    private String email;
    private String address;
}
