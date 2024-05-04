package com.example.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.backend.entity.CONSULTATION_SESSION_STATUS;
import com.example.backend.entity.CONSULTATION_SESSION_CLOSURE_REASON;
import com.example.backend.entity.CONSULTATION_SESSION_TYPE;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationSessionDTO {
    private Long consultationRequestId;
    private Long webrtcSessionId;
    private CONSULTATION_SESSION_STATUS status;
    private CONSULTATION_SESSION_CLOSURE_REASON closureReason;
    private CONSULTATION_SESSION_TYPE type;
    private Long patientId;
    private Long doctorId;
    private Long organizationId;
    private Long supervisorDoctorId;
}
