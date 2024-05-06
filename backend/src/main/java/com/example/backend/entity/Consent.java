package com.example.backend.entity;

import java.time.LocalDateTime;

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
@Table(name = "consent")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Consent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CONSENT_TYPE type = CONSENT_TYPE.LIST_ALL_EHR;

    @JoinColumn(name = "patient_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Patient.class, fetch = FetchType.EAGER)
    @Transient
    private Patient patient;
    @Column(name = "patient_id")
    private Long patientId;

    @JoinColumn(name = "doctor_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Doctor.class, fetch = FetchType.EAGER)
    @Transient
    private Doctor doctor;
    @Column(name = "doctor_id")
    private Long doctorId;

    @JoinColumn(name = "ehr_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = EHR.class, fetch = FetchType.EAGER)
    @Transient
    private EHR ehr;
    @Column(name = "ehr_id")
    private Long ehrId;

    private CONSENT_STATUS status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Consent(CONSENT_TYPE type, Long patientId, Long doctorId, Long ehrId,
        CONSENT_STATUS status, LocalDateTime startDate, LocalDateTime endDate)
    {
        this.type = type;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.ehrId = ehrId;
        this.status = status;
        this.startDate = startDate;
        this.startDate = startDate;
    }
}
