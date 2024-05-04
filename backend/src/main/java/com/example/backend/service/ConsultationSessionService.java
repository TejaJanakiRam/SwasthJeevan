package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.dto.ConsultationSessionDTO;
import com.example.backend.entity.ConsultationSession;

@Service
public interface ConsultationSessionService {
    public Long create(ConsultationSessionDTO consultationSessionDTO) throws Exception;
    public List<ConsultationSession> list(String idStr, String consultationRequestIdStr,
        String webrtcSessionIdStr, String statusStr, String closureReasonStr,
        String typeStr, String patientIdStr, String doctorIdStr,
        String organizationIdStr, String supervisorDoctorIdStr) throws Exception;
    public ConsultationSession join(Long consultationSessionId, Long doctorId) throws Exception;
    public ConsultationSession leave(Long consultationSessionId, Long doctorId) throws Exception;
    public ConsultationSession get(Long consultationSessionId) throws Exception;
    public Long close(Long consultationSessionId) throws Exception;
}
