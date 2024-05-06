package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.dto.ConsultationRecordDTO;
import com.example.backend.entity.ConsultationRecord;

@Service
public interface ConsultationRecordService {
    public Long create(ConsultationRecordDTO consultationRecordDTO) throws Exception;
    public List<ConsultationRecord> list(String patientIdStr, String doctorIdStr,
                                        String organizationIdStr, String issueDateStr,
                                        String endDateStr) throws Exception;
    public ConsultationRecord get(Long consultationRecordId) throws Exception;
    public Long update(Long consultationRecordId, ConsultationRecordDTO consultationRecordDTO) throws Exception;
    public Long delete(Long consultationRecordId) throws Exception;
}
