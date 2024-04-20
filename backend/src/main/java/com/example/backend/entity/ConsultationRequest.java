package com.example.backend.entity;

import com.example.backend.types.SPECIALITY_TYPE;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consultation_request")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsultationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CONSULTATION_REQUEST_TYPE type = CONSULTATION_REQUEST_TYPE.CONSULTATION;
    private Long patientId;
    private SPECIALITY_TYPE speciality = SPECIALITY_TYPE.GENERAL_PHYSICIAN;
    private Long organizationId;
    private Long doctorId;
    private Long referringOrganizationId;
    private Long referringDoctorId;
    private CONSULTATION_REQUEST_STATUS status = CONSULTATION_REQUEST_STATUS.OPEN;

    public ConsultationRequest(CONSULTATION_REQUEST_TYPE type,
                              Long patientId, SPECIALITY_TYPE speciality,
                              Long organizationId, Long doctorId,
                              Long referringOrganizationId, Long referringDoctorId,
                              CONSULTATION_REQUEST_STATUS status)
    {
        this.type = type;
        this.patientId = patientId;
        this.speciality = speciality;
        this.organizationId = organizationId;
        this.doctorId = doctorId;
        this.referringDoctorId = referringDoctorId;
        this.referringOrganizationId = referringOrganizationId;
        this.status = status;
    }
}
