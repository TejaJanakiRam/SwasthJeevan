package com.example.backend.mapper;

import com.example.backend.dto.ConsultationRecordDTO;
import com.example.backend.entity.ConsultationRecord;

public class ConsultationRecordMapper {

    public static ConsultationRecord mapToConsultationRecord(ConsultationRecordDTO consultationRecordDTO) {
        ConsultationRecord consultationRecord = new ConsultationRecord(
            consultationRecordDTO.getRequestId(),
            consultationRecordDTO.getPatientId(),
            consultationRecordDTO.getDoctorId(),
            consultationRecordDTO.getOrganizationId(),
            consultationRecordDTO.getSymptoms(),
            consultationRecordDTO.getDiagnosis(),
            consultationRecordDTO.getDisease(),
            consultationRecordDTO.getIssueDate(),
            consultationRecordDTO.getEndDate(),
            consultationRecordDTO.getNotes(),
            consultationRecordDTO.getMedicines());
        return(consultationRecord);
    }

    public static ConsultationRecordDTO mapToConsultationRecordDTO(ConsultationRecord consultationRecord) {
        ConsultationRecordDTO consultationRecordDTO = new ConsultationRecordDTO(
            consultationRecord.getRequestId(),
            consultationRecord.getPatientId(),
            consultationRecord.getDoctorId(),
            consultationRecord.getOrganizationId(),
            consultationRecord.getSymptoms(),
            consultationRecord.getDiagnosis(),
            consultationRecord.getDisease(),
            consultationRecord.getIssueDate(),
            consultationRecord.getEndDate(),
            consultationRecord.getNotes(),
            consultationRecord.getMedicines());
        return(consultationRecordDTO);
    }
}
