package com.example.backend.response;

import java.util.List;

import com.example.backend.entity.ConsultationRecord;

import lombok.Data;

@Data
public class ListConsultationRecordsResponse {
    List<ConsultationRecord> consultationRecords;
    String message;
}
