package com.example.backend.dto;

import com.example.backend.entity.CONSULTATION_REQUEST_STATUS;
import com.example.backend.entity.CONSULTATION_REQUEST_TYPE;
import com.example.backend.types.SPECIALITY_TYPE;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRequestDTO {
    private CONSULTATION_REQUEST_TYPE type = CONSULTATION_REQUEST_TYPE.CONSULTATION;
    private Long patientId;
    private SPECIALITY_TYPE speciality = SPECIALITY_TYPE.GENERAL_PHYSICIAN;
    private Long organizationId;
    private Long doctorId;
    private Long referringOrganizationId;
    private Long referringDoctorId;
    private CONSULTATION_REQUEST_STATUS status = CONSULTATION_REQUEST_STATUS.OPEN;
}
