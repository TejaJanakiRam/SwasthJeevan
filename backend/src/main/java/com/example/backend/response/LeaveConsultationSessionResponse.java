package com.example.backend.response;

import com.example.backend.entity.ConsultationSession;

import lombok.Data;

@Data
public class LeaveConsultationSessionResponse {
    ConsultationSession consultationSession;
    String message;
}
