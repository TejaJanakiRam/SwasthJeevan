package com.example.backend.entity;

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
@Table(name = "consent")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Consent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sessionId;
    private CONSENT_TYPE type = CONSENT_TYPE.LIST_ALL_EHR;
    private Long patientId;
    private Long doctorId;
    private Long ehrId;
    private CONSENT_STATUS status;

    public Consent(Long sessionId, CONSENT_TYPE type, Long patientId, Long doctorId, Long ehrId, CONSENT_STATUS status)
    {
        this.sessionId = sessionId;
        this.type = type;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.ehrId = ehrId;
        this.status = status;
    }
}
