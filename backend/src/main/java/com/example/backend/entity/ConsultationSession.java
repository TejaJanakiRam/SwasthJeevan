package com.example.backend.entity;

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
@Table(name = "consultation_session")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ConsultationSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "consultation_request_id", referencedColumnName="id", insertable = false, updatable = false)
    @OneToOne(targetEntity = ConsultationRequest.class, fetch = FetchType.EAGER)
    @Transient
    private ConsultationRequest consultationRequest;
    @Column(name = "consultation_request_id")
    private Long consultationRequestId;

    //@JoinColumn(name = "webrtc_session_id", referencedColumnName="id", insertable = false, updatable = false)
    //@OneToOne(targetEntity = WebrtcSession.class, fetch = FetchType.EAGER)
    //@Transient
    //private WebrtcSession webrtcSession;
    //@Column(name = "webrtc_session_id")
    private Long webrtcSessionId;

    private CONSULTATION_SESSION_STATUS status = CONSULTATION_SESSION_STATUS.STARTED;
    private CONSULTATION_SESSION_CLOSURE_REASON closureReason;
    private CONSULTATION_SESSION_TYPE type;

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

    @JoinColumn(name = "supervisor_doctor_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Doctor.class, fetch = FetchType.EAGER)
    @Transient
    private Doctor supervisorDoctor;
    @Column(name = "supervisor_doctor_id")
    private Long supervisorDoctorId;

    public ConsultationSession(Long consultationRequestId, Long webrtcSessionId,
        CONSULTATION_SESSION_STATUS status, CONSULTATION_SESSION_CLOSURE_REASON closureReason,
        CONSULTATION_SESSION_TYPE type, Long patientId, Long doctorId, Long organizationId,
        Long supervisorDoctorId)
    {
        this.consultationRequestId = consultationRequestId;
        this.webrtcSessionId = webrtcSessionId;
        this.status = status;
        this.closureReason = closureReason;
        this.type = type;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.organizationId = organizationId;
        this.supervisorDoctorId = supervisorDoctorId;
    }

    public ConsultationSession(Long id, Long consultationRequestId, Long webrtcSessionId,
        CONSULTATION_SESSION_STATUS status, CONSULTATION_SESSION_CLOSURE_REASON closureReason,
        CONSULTATION_SESSION_TYPE type, Long patientId, Long doctorId, Long organizationId,
        Long supervisorDoctorId)
    {
        this.id = id;
        this.consultationRequestId = consultationRequestId;
        this.webrtcSessionId = webrtcSessionId;
        this.status = status;
        this.closureReason = closureReason;
        this.type = type;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.organizationId = organizationId;
        this.supervisorDoctorId = supervisorDoctorId;
    }
}
