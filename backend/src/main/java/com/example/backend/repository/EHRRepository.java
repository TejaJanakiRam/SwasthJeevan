package com.example.backend.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.entity.EHR;
import com.example.backend.entity.EHR_TYPE;

public interface EHRRepository extends JpaRepository<EHR, Long> {
    @Query("SELECT e FROM EHR e WHERE (:userId is null or e.userId = :userId) and"
    + "(:type is null or e.type = :type) and (:diagnosisId is null or e.diagnosisId = :diagnosisId) and"
    + "(:fromDate is null or e.issueDate > :fromDate) and (:toDate is null or e.issueDate < :toDate)")
    public List<EHR> findByUserIdAndTypeAndDiagnosisIdAndIssueDateGreaterThanEqualAndIssueDateLessThanEqual(Long userId, EHR_TYPE type, Long diagnosisId, Date fromDate, Date toDate) throws Exception;
    public Optional<EHR> findByUserIdAndTypeAndDiagnosisIdAndIssueDateAndEndDate(Long userId, EHR_TYPE type, Long diagnosisId, Date issueDate, Date endDate) throws Exception;
}
