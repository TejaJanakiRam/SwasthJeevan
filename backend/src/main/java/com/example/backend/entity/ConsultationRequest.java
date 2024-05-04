package com.example.backend.entity;

import com.example.backend.types.SPECIALITY_TYPE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

    @JoinColumn(name = "patient_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Patient.class, fetch = FetchType.EAGER)
    @Transient
    private Patient patient;
    @Column(name = "patient_id")
    private Long patientId;

    private SPECIALITY_TYPE speciality = SPECIALITY_TYPE.GENERAL_PHYSICIAN;

    @JoinColumn(name = "organization_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.EAGER)
    @Transient
    private Organization organization;
    @Column(name = "organization_id")
    private Long organizationId;

    @JoinColumn(name = "doctor_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Doctor.class, fetch = FetchType.EAGER)
    @Transient
    private Doctor doctor;
    @Column(name = "doctor_id")
    private Long doctorId;

    @JoinColumn(name = "referring_organization_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.EAGER)
    @Transient
    private Organization referringOrganization;
    @Column(name = "referring_organization_id")
    private Long referringOrganizationId;

    @JoinColumn(name = "referring_doctor_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Doctor.class, fetch = FetchType.EAGER)
    @Transient
    private Doctor referringDoctor;
    @Column(name = "referring_doctor_id")
    private Long referringDoctorId;

    private CONSULTATION_REQUEST_STATUS status = CONSULTATION_REQUEST_STATUS.CREATED;

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

    public ConsultationRequest(Long id, CONSULTATION_REQUEST_TYPE type,
                              Long patientId, SPECIALITY_TYPE speciality,
                              Long organizationId, Long doctorId,
                              Long referringOrganizationId, Long referringDoctorId,
                              CONSULTATION_REQUEST_STATUS status)
    {
        this.id = id;
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
