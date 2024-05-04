package com.example.backend.mapper;

import com.example.backend.dto.ConsultationSessionDTO;
import com.example.backend.entity.ConsultationSession;

public class ConsultationSessionMapper {
    public static ConsultationSession mapToConsultationSession(ConsultationSessionDTO ConsultationSessionDTO) {
        ConsultationSession ConsultationSession =
            new ConsultationSession(ConsultationSessionDTO.getConsultationRequestId(),
            ConsultationSessionDTO.getWebrtcSessionId(), ConsultationSessionDTO.getStatus(),
            ConsultationSessionDTO.getClosureReason(), ConsultationSessionDTO.getType(),
            ConsultationSessionDTO.getPatientId(), ConsultationSessionDTO.getDoctorId(),
            ConsultationSessionDTO.getOrganizationId(),
            ConsultationSessionDTO.getSupervisorDoctorId());
        return(ConsultationSession);
    }

    public static ConsultationSessionDTO mapToConsultationSessionDTO(ConsultationSession ConsultationSession) {
        ConsultationSessionDTO ConsultationSessionDTO =
            new ConsultationSessionDTO(ConsultationSession.getConsultationRequestId(),
            ConsultationSession.getWebrtcSessionId(), ConsultationSession.getStatus(),
            ConsultationSession.getClosureReason(), ConsultationSession.getType(),
            ConsultationSession.getPatientId(), ConsultationSession.getDoctorId(),
            ConsultationSession.getOrganizationId(),
            ConsultationSession.getSupervisorDoctorId());
        return(ConsultationSessionDTO);
    }
}
