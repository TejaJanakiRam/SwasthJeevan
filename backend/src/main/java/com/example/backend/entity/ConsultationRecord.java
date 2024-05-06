package com.example.backend.entity;

import java.time.LocalDateTime;

import com.example.backend.types.DIAGNOSIS_TYPE;
import com.example.backend.types.DISEASE_TYPE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "consultation_record")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsultationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "request_id", referencedColumnName="id", insertable = false, updatable = false)
    @OneToOne(targetEntity = ConsultationRequest.class, fetch = FetchType.EAGER)
    @Transient
    private ConsultationRequest consultationRequest;
    @Column(name = "request_id")
    private Long requestId;

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

    @JoinColumn(name = "organization_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.EAGER)
    @Transient
    private Organization organization;
    @Column(name = "organization_id")
    private Long organizationId;

    @Column(length = 512)
    private String symptoms;
    private DIAGNOSIS_TYPE diagnosis;
    private DISEASE_TYPE disease;
    private LocalDateTime issueDate;
    private LocalDateTime endDate;
    @Column(length = 2048)
    private String notes;
    @Column(length = 2048)
    private String medicines;

    public ConsultationRecord(Long requestId, Long patientId, Long doctorId,
        Long organizationId, String symptoms, DIAGNOSIS_TYPE diagnosis,
        DISEASE_TYPE disease, LocalDateTime issueDate, LocalDateTime endDate,
        String notes, String medicines)
    {
        this.requestId = requestId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.organizationId = organizationId;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.disease = disease;
        this.issueDate = issueDate;
        this.endDate = endDate;
        this.notes = notes;
        this.medicines = medicines;
    }

    public ConsultationRecord(Long id, Long requestId, Long patientId, Long doctorId,
        Long organizationId, String symptoms, DIAGNOSIS_TYPE diagnosis,
        DISEASE_TYPE disease, LocalDateTime issueDate, LocalDateTime endDate,
        String notes, String medicines)
    {
        this.id = id;
        this.requestId = requestId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.organizationId = organizationId;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.disease = disease;
        this.issueDate = issueDate;
        this.endDate = endDate;
        this.notes = notes;
        this.medicines = medicines;
    }
}
