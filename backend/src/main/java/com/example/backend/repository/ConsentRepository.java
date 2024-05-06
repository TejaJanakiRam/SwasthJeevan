package com.example.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.entity.CONSENT_STATUS;
import com.example.backend.entity.CONSENT_TYPE;
import com.example.backend.entity.Consent;

public interface ConsentRepository extends JpaRepository<Consent, Long> {
    @Query("SELECT c FROM Consent c WHERE (c.type = :type) and "
    + "(c.patientId = :patientId) and (c.doctorId = :doctorId) and "
    + "(:ehrId is null or c.ehrId = :ehrId) and (c.status = :status)")
    public Optional<Consent> findByTypeAndPatientIdAndDoctorIdAndEhrIdAndStatus(
        CONSENT_TYPE type, Long patientId, Long doctorId, Long ehrId, CONSENT_STATUS status
    );

    @Query("SELECT c FROM Consent c WHERE (c.patientId = :patientId) and "
    + "(c.doctorId = :doctorId) and (c.type = :type) and (:ehrId is null or c.ehrId = :ehrId)")
    public Optional<Consent> findByPatientIdAndDoctorIdAndTypeAndEhrId(
        Long patientId, Long doctorId, CONSENT_TYPE type, Long ehrId
    );

    @Query("SELECT c FROM Consent c WHERE (:type is null or c.type = :type) and "
    + "(c.patientId = :patientId) and (:doctorId is null or c.doctorId = :doctorId) and "
    + "(:ehrId is null or c.ehrId = :ehrId) and (:status is null or c.status = :status) and "
    + "(:startDate is null or c.startDate > :startDate) and (:endDate is null or c.endDate < :endDate)")
    public List<Consent> findByTypeAndPatientIdAndDoctorIdAndEhrIdAndStatusAndStartDateGreaterThanAndEndDateLessThan(
        CONSENT_TYPE type, Long patientId, Long doctorId, Long ehrId,
        CONSENT_STATUS status, LocalDateTime startDate, LocalDateTime endDate
    );
}
