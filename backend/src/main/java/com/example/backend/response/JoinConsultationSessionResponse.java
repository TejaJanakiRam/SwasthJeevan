package com.example.backend.response;

import com.example.backend.entity.ConsultationSession;

import lombok.Data;

@Data
public class JoinConsultationSessionResponse {
    ConsultationSession consultationSession;
    String message;
}
