package com.example.backend.response;

import java.util.List;

import com.example.backend.entity.ConsultationSession;

import lombok.Data;

@Data
public class ListConsultationSessionsResponse {
    List<ConsultationSession> consultationSessions;
    String message;
}
