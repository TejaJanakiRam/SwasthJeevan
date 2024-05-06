package com.example.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.entity.EHR;
import com.example.backend.entity.EHR_TYPE;
import com.example.backend.request.EHRMetadata;
import com.example.backend.types.DIAGNOSIS_TYPE;

public interface EHRRepository extends JpaRepository<EHR, Long> {
    @Query("SELECT new com.example.backend.request.EHRMetadata(e.id, e.userId, e.type, e.diagnosisType, e.issueDate, e.endDate) FROM EHR e WHERE (:userId is null or e.userId = :userId) and"
    + "(:type is null or e.type = :type) and (:diagnosisType is null or e.diagnosisType = :diagnosisType) and"
    + "(:fromDate is null or e.issueDate > :fromDate) and (:toDate is null or e.issueDate < :toDate)")
    public List<EHRMetadata> findByUserIdAndTypeAndDiagnosisTypeAndIssueDateGreaterThanAndIssueDateLessThan(Long userId, EHR_TYPE type, DIAGNOSIS_TYPE diagnosisType, LocalDateTime fromDate, LocalDateTime toDate) throws Exception;
    public Optional<EHR> findByUserIdAndTypeAndDiagnosisTypeAndIssueDateAndEndDate(Long userId, EHR_TYPE type, DIAGNOSIS_TYPE diagnosisType, LocalDateTime issueDate, LocalDateTime endDate) throws Exception;
}
