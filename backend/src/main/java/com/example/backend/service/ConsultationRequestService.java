package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.dto.ConsultationRequestDTO;
import com.example.backend.entity.ConsultationRequest;

@Service
public interface ConsultationRequestService {
    public Long create(ConsultationRequestDTO consultationRequestDTO) throws Exception;
    public Long book(ConsultationRequest consultationRequest) throws Exception;
    public List<ConsultationRequest> list(String statusStr, String typeStr,
                                          String specialityStr, String patientIdStr,
                                          String organizationIdStr,
                                          String doctorIdStr) throws Exception;
    public ConsultationRequest get(Long consultationRequestId) throws Exception;
    public Long close(Long consultationRequestId) throws Exception;
    public Long delete(Long consultationRequestId) throws Exception;
}
