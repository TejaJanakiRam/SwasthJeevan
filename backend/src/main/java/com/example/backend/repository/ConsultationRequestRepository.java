package com.example.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.entity.CONSULTATION_REQUEST_STATUS;
import com.example.backend.entity.CONSULTATION_REQUEST_TYPE;
import com.example.backend.entity.ConsultationRequest;
import com.example.backend.types.SPECIALITY_TYPE;

public interface ConsultationRequestRepository  extends JpaRepository<ConsultationRequest, Long> {

    public Optional<ConsultationRequest> findByPatientIdAndDoctorIdAndTypeAndStatus(
        Long patientId, Long doctorId,
        CONSULTATION_REQUEST_TYPE type, CONSULTATION_REQUEST_STATUS status);

    @Query("SELECT new com.example.backend.entity.ConsultationRequest(c.id, c.type, c.patientId, c.speciality, c.organizationId, c.doctorId, c.referringOrganizationId, c.referringDoctorId, c.status) FROM ConsultationRequest c WHERE "
    + "(:status is null or c.status = :status) and (:type is null or c.type = :type) and"
    + "(:speciality is null or c.speciality = :speciality) and (:patientId is null or c.patientId = :patientId) and"
    + "(:organizationId is null or c.organizationId = :organizationId) and (:doctorId is null or c.doctorId = :doctorId)")
    public List<ConsultationRequest> findByStatusAndTypeAndSpecialityAndPatientIdAndOrganizationIdAndDoctorId(
            CONSULTATION_REQUEST_STATUS status, CONSULTATION_REQUEST_TYPE type, SPECIALITY_TYPE speciality,
            Long patientId, Long organizationId, Long doctorId);
}
