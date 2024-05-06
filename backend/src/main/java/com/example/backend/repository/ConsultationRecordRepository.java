package com.example.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.entity.ConsultationRecord;

public interface ConsultationRecordRepository extends JpaRepository<ConsultationRecord, Long> {

    Optional<ConsultationRecord> findByRequestIdAndPatientIdAndDoctorId(
        Long requestId, Long patientId, Long doctorId);

    @Query("SELECT c FROM ConsultationRecord c WHERE "
    + "(:patientId is null or c.patientId = :patientId) and (c.doctorId = :doctorId) and "
    + "(:organizationId is null or c.organizationId = :organizationId) and "
    + "(:issueDate is null or c.issueDate > :issueDate) and "
    + "(:endDate is null or c.endDate < :endDate)")
    public List<ConsultationRecord> findByPatientIdAndDoctorIdAndOrganizationIdAndIssueDateGreaterThanAndEndDateLessThan(
        Long patientId, Long doctorId, Long organizationId, LocalDateTime issueDate, LocalDateTime endDate);

}
