package com.example.backend.response;

import com.example.backend.entity.ConsultationRecord;

import lombok.Data;

@Data
public class GetConsultationRecordResponse {
    ConsultationRecord consultationRecord;
    String message;
}
