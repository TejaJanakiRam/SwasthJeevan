package com.example.backend.service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.ConsultationRecordDTO;
import com.example.backend.entity.ConsultationRecord;
import com.example.backend.mapper.ConsultationRecordMapper;
import com.example.backend.repository.ConsultationRecordRepository;
import com.example.backend.service.ConsultationRecordService;

@Service
public class ConsultationRecordServiceImpl implements ConsultationRecordService {

    @Autowired
    private ConsultationRecordRepository consultationRecordRepository;
    
    @Override
    public Long create(ConsultationRecordDTO consultationRecordDTO) throws Exception {
        ConsultationRecord consultationRecord =
            ConsultationRecordMapper.mapToConsultationRecord(consultationRecordDTO);
        Optional<ConsultationRecord> consultationRecordRec =
            consultationRecordRepository.findByRequestIdAndPatientIdAndDoctorId(
                consultationRecord.getRequestId(), consultationRecord.getPatientId(),
                consultationRecord.getDoctorId());
        if (consultationRecordRec.isPresent()) {
            throw new Exception("Consultation record already exists");
        }
        ConsultationRecord savedConsultationRecord =
            consultationRecordRepository.save(consultationRecord);
        return savedConsultationRecord.getId();
    }

    @Override
    public List<ConsultationRecord> list(String patientIdStr, String doctorIdStr,
                                        String organizationIdStr, String issueDateStr,
                                        String endDateStr) throws Exception {
        Long patientId = (patientIdStr != null) ? Long.valueOf(patientIdStr) : null;
        Long doctorId = (doctorIdStr != null) ? Long.valueOf(doctorIdStr) : null;
        Long organizationId = (organizationIdStr != null) ? Long.valueOf(organizationIdStr)  : null;
        LocalDateTime issueDate = (issueDateStr != null) ? LocalDateTime.parse(issueDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        LocalDateTime endDate = (endDateStr != null) ? LocalDateTime.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        List<ConsultationRecord> consultationRecords = new ArrayList<ConsultationRecord>(
            consultationRecordRepository.findByPatientIdAndDoctorIdAndOrganizationIdAndIssueDateGreaterThanAndEndDateLessThan(
                patientId, doctorId, organizationId, issueDate, endDate));
        if (consultationRecords.isEmpty()) {
            throw new Exception("Consultation record(s) not found");
        }
        return consultationRecords;
    }

    @Override
    public ConsultationRecord get(Long consultationRecordId) throws Exception {
        Optional<ConsultationRecord> consultationRecord =
            consultationRecordRepository.findById(consultationRecordId);
        if (consultationRecord.isEmpty()) {
            throw new Exception("Consultation record not found");
        }
        return (consultationRecord.get());
    }

    @Override
    public Long update(Long consultationRecordId,
                       ConsultationRecordDTO consultationRecordDTO) throws Exception {
        ConsultationRecord consultationRecord =
            ConsultationRecordMapper.mapToConsultationRecord(consultationRecordDTO);
        Optional<ConsultationRecord> optionalConsultationRecord =
            consultationRecordRepository.findById(consultationRecordId);
        if (!optionalConsultationRecord.isEmpty()) {
            ConsultationRecord existingConsultationRecord = optionalConsultationRecord.get();
            consultationRecord.setId(existingConsultationRecord.getId());
        }
        consultationRecordRepository.save(consultationRecord);
        return (consultationRecordId);
    }

    @Override
    public Long delete(Long consultationRecordId) throws Exception {
        Optional<ConsultationRecord> consultationRecord =
            consultationRecordRepository.findById(consultationRecordId);
        if (consultationRecord.isEmpty()) {
            throw new Exception("Consultation record not found");
        }
        consultationRecordRepository.deleteById(consultationRecordId);
        return (consultationRecordId);
    }
}
