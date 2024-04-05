package com.example.backend.dto;

import com.example.backend.entity.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class OrganizartionAdminDTO {
    private Organization orgid;
}
