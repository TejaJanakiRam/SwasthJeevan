package com.example.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.entity.CONSENT_STATUS;
import com.example.backend.entity.CONSENT_TYPE;
import com.example.backend.entity.Consent;

public interface ConsentRepository extends JpaRepository<Consent, Long> {
    @Query("SELECT c FROM Consent c WHERE (c.sessionId = :sessionId) and"
    + "(c.type = :type) and (c.patientId = :patientId) and"
    + "(c.doctorId = :doctorId) and (:ehrId is null or c.ehrId = :ehrId) and"
    + "(c.status = :status)")
    public Optional<Consent> findBySessionIdAndTypeAndPatientIdAndDoctorIdAndEhrIdAndStatus(
        Long sessionId, CONSENT_TYPE type, Long patientId,
        Long doctorId, Long ehrId, CONSENT_STATUS status
    );
}
