package com.example.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.entity.CONSULTATION_SESSION_CLOSURE_REASON;
import com.example.backend.entity.CONSULTATION_SESSION_STATUS;
import com.example.backend.entity.CONSULTATION_SESSION_TYPE;
import com.example.backend.entity.ConsultationSession;

public interface ConsultationSessionRepository  extends JpaRepository<ConsultationSession, Long> {

    public Optional<ConsultationSession>
        findByConsultationRequestIdAndPatientIdAndDoctorIdAndTypeAndStatus(
        Long consultationRequestId, Long patientId, Long doctorId,
        CONSULTATION_SESSION_TYPE type, CONSULTATION_SESSION_STATUS status);

    @Query("SELECT new com.example.backend.entity.ConsultationSession(c.id, "
    + "c.consultationRequestId, c.webrtcSessionId, c.status, c.closureReason, "
    + "c.type, c.patientId, c.doctorId, c.organizationId, c.supervisorDoctorId) "
    + "FROM ConsultationSession c WHERE (:id is null or c.id = :id) and "
    + "(:consultationRequestId is null or c.consultationRequestId = :consultationRequestId) and "
    + "(:webrtcSessionId is null or c.webrtcSessionId = :webrtcSessionId) and "
    + "(:status is null or c.status = :status) and "
    + "(:closureReason is null or c.closureReason = :closureReason) and "
    + "(:type is null or c.type = :type) and (:patientId is null or c.patientId = :patientId) and "
    + "(:doctorId is null or c.doctorId = :doctorId) and "
    + "(:organizationId is null or c.organizationId = :organizationId) and "
    + "(:supervisorDoctorId is null or c.supervisorDoctorId = :supervisorDoctorId)")
    public List<ConsultationSession>
        findByIdAndConsultationRequestIdAndWebrtcSessionIdAndStatusAndClosureReasonAndTypeAndPatientIdAndDoctorIdAndOrganizationIdAndSupervisorDoctorId(
        Long id, Long consultationRequestId, Long webrtcSessionId,
        CONSULTATION_SESSION_STATUS status, CONSULTATION_SESSION_CLOSURE_REASON closureReason,
        CONSULTATION_SESSION_TYPE type, Long patientId, Long doctorId, Long organizationId,
        Long supervisorDoctorId);

    public Optional<ConsultationSession>
        findByIdAndSupervisorDoctorId(Long consultationSessionId, Long doctorId);
}
