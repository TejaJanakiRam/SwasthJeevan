package com.example.backend.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.ConsultationSessionDTO;
import com.example.backend.entity.CONSULTATION_SESSION_CLOSURE_REASON;
import com.example.backend.entity.CONSULTATION_SESSION_STATUS;
import com.example.backend.entity.CONSULTATION_SESSION_TYPE;
import com.example.backend.entity.ConsultationSession;
import com.example.backend.mapper.ConsultationSessionMapper;
import com.example.backend.repository.ConsultationSessionRepository;
import com.example.backend.service.ConsultationSessionService;

@Service
public class ConsultationSessionServiceImpl implements ConsultationSessionService {

    @Autowired
    private ConsultationSessionRepository consultationSessionRepository;
    
    @Override
    public Long create(ConsultationSessionDTO consultationSessionDTO) throws Exception {
        ConsultationSession consultationSession =
            ConsultationSessionMapper.mapToConsultationSession(consultationSessionDTO);
        Optional<ConsultationSession> consultationSessionRec =
            consultationSessionRepository.findByConsultationRequestIdAndPatientIdAndDoctorIdAndTypeAndStatus(
                consultationSession.getConsultationRequestId(), consultationSession.getPatientId(),
                consultationSession.getDoctorId(), consultationSession.getType(),
                consultationSession.getStatus());
        if (consultationSessionRec.isPresent()) {
            throw new Exception("Consulatation session already exists");
        }
        ConsultationSession savedConsultationSession =
            consultationSessionRepository.save(consultationSession);
        return savedConsultationSession.getId();
    }

    @Override
    public List<ConsultationSession> list(String idStr, String consultationRequestIdStr,
        String webrtcSessionIdStr, String statusStr, String closureReasonStr,
        String typeStr, String patientIdStr, String doctorIdStr,
        String organizationIdStr, String supervisorDoctorIdStr) throws Exception {

        Long id = (idStr != null && !idStr.isEmpty())? Long.valueOf(idStr) : null;
        Long consultationRequestId =
            (consultationRequestIdStr != null && !consultationRequestIdStr.isEmpty()) ? Long.valueOf(consultationRequestIdStr) : null;
        Long webrtcSessionId =
            (webrtcSessionIdStr != null && !webrtcSessionIdStr.isEmpty()) ? Long.valueOf(webrtcSessionIdStr) : null;
        CONSULTATION_SESSION_STATUS status;
        if (statusStr != null) {
            try {
                status = CONSULTATION_SESSION_STATUS.valueOf(statusStr);
            } catch (Exception e) {
                status = null;
            }
        } else {
            status = null;
        }
        CONSULTATION_SESSION_CLOSURE_REASON closureReason;
        if (closureReasonStr != null) {
            try {
                closureReason = CONSULTATION_SESSION_CLOSURE_REASON.valueOf(closureReasonStr);
            } catch (Exception e) {
                closureReason = null;
            }
        } else {
            closureReason = null;
        }
        CONSULTATION_SESSION_TYPE type;
        if (typeStr != null) {
            try {
                type = CONSULTATION_SESSION_TYPE.valueOf(typeStr);
            } catch (Exception e) {
                type = null;
            }
        } else {
            type = null;
        }
        Long patientId =
            (patientIdStr != null && !patientIdStr.isEmpty()) ? Long.valueOf(patientIdStr) : null;
        Long organizationId =
            (organizationIdStr != null && !organizationIdStr.isEmpty()) ? Long.valueOf(organizationIdStr)  : null;
        Long doctorId =
            (doctorIdStr != null && !doctorIdStr.isEmpty()) ? Long.valueOf(doctorIdStr) : null;
        Long supervisorDoctorId =
            (supervisorDoctorIdStr != null && !supervisorDoctorIdStr.isEmpty()) ? Long.valueOf(supervisorDoctorIdStr) : null;

        List<ConsultationSession> consultationSessions = new ArrayList<ConsultationSession>(
            consultationSessionRepository.findByIdAndConsultationRequestIdAndWebrtcSessionIdAndStatusAndClosureReasonAndTypeAndPatientIdAndDoctorIdAndOrganizationIdAndSupervisorDoctorId(
                id, consultationRequestId, webrtcSessionId, status, closureReason,
                type, patientId, doctorId, organizationId, supervisorDoctorId));
        if (consultationSessions.isEmpty()) {
            throw new Exception("Consultation session(s) not found");
        }
        return consultationSessions;
    }

    @Override
    public ConsultationSession get(Long consultationSessionId) throws Exception {
        Optional<ConsultationSession> consultationSession =
            consultationSessionRepository.findById(consultationSessionId);
        if (consultationSession.isEmpty()) {
            throw new Exception("Consultation session not found");
        }
        return (consultationSession.get());
    }

    @Override
    public ConsultationSession join(Long consultationSessionId,
        Long doctorId) throws Exception {
        Optional<ConsultationSession> consultationSessionOpt =
            consultationSessionRepository.findById(consultationSessionId);
        if (consultationSessionOpt.isEmpty()) {
            throw new Exception("Consultation session not found");
        }
        // ADD CODE FOR WEBRTC JOIN
        ConsultationSession consultationSession = consultationSessionOpt.get();
        consultationSession.setSupervisorDoctorId(doctorId);
        consultationSessionRepository.save(consultationSession);
        return (consultationSession);
    }

    @Override
    public ConsultationSession leave(Long consultationSessionId,
        Long doctorId) throws Exception {
        Optional<ConsultationSession> consultationSessionOpt =
            consultationSessionRepository.findByIdAndSupervisorDoctorId(
                consultationSessionId, doctorId);
        if (consultationSessionOpt.isEmpty()) {
            throw new Exception("Consultation session not found");
        }
        // ADD CODE FOR WEBRTC LEAVE
        return (consultationSessionOpt.get());
    }

    @Override
    public Long close(Long consultationSessionId) throws Exception {
        Optional<ConsultationSession> optionalConsultationSession =
            consultationSessionRepository.findById(consultationSessionId);
        if (optionalConsultationSession.isEmpty()) {
            throw new Exception("Consultation session not found");
        }
        ConsultationSession consultationSession = optionalConsultationSession.get();
        consultationSession.setStatus(CONSULTATION_SESSION_STATUS.CLOSED);
        consultationSessionRepository.save(consultationSession);
        return (consultationSessionId);
    }
}
