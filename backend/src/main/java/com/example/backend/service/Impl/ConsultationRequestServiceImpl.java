package com.example.backend.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.dto.ConsultationRequestDTO;
import com.example.backend.entity.CONSULTATION_REQUEST_STATUS;
import com.example.backend.entity.CONSULTATION_REQUEST_TYPE;
import com.example.backend.entity.ConsultationRequest;
import com.example.backend.mapper.ConsultationRequestMapper;
import com.example.backend.repository.ConsultationRequestRepository;
import com.example.backend.service.ConsultationRequestService;
import com.example.backend.types.SPECIALITY_TYPE;

@Service
public class ConsultationRequestServiceImpl implements ConsultationRequestService {

    @Autowired
    private ConsultationRequestRepository consultationRequestRepository;
    
    @Override
    public Long create(ConsultationRequestDTO consultationRequestDTO) throws Exception {
        ConsultationRequest consultationRequest =
            ConsultationRequestMapper.mapToConsultationRequest(consultationRequestDTO);
        Optional<ConsultationRequest> consultationRequestRec =
            consultationRequestRepository.findByPatientIdAndDoctorIdAndTypeAndStatus(
                consultationRequest.getPatientId(), consultationRequest.getDoctorId(),
                consultationRequest.getType(), consultationRequest.getStatus());
        if (consultationRequestRec.isPresent()) {
            throw new Exception("Consulatation request already exists");
        }
        ConsultationRequest savedConsultationRequest =
            consultationRequestRepository.save(consultationRequest);
        return savedConsultationRequest.getId();
    }

    @Override
    public Long book(ConsultationRequest consultationRequest) throws Exception {
        return (long) 0;
    }

    @Override
    public List<ConsultationRequest> list(String statusStr, String typeStr,
                                          String specialityStr, String patientIdStr,
                                          String organizationIdStr,
                                          String doctorIdStr) throws Exception {
        CONSULTATION_REQUEST_STATUS status;
        if (statusStr != null) {
            try {
                status = CONSULTATION_REQUEST_STATUS.valueOf(statusStr);
            } catch (Exception e) {
                status = null;
            }
        } else {
            status = null;
        }
        CONSULTATION_REQUEST_TYPE type;
        if (typeStr != null) {
            try {
                type = CONSULTATION_REQUEST_TYPE.valueOf(typeStr);
            } catch (Exception e) {
                type = null;
            }
        } else {
            type = null;
        }
        SPECIALITY_TYPE speciality;
        if (specialityStr != null) {
            try {
                speciality = SPECIALITY_TYPE.valueOf(specialityStr);
            } catch (Exception e) {
                speciality = null;
            }
        } else {
            speciality = null;
        }
        Long patientId = (patientIdStr != null) ? Long.valueOf(patientIdStr) : null;
        Long organizationId = (organizationIdStr != null) ? Long.valueOf(organizationIdStr)  : null;
        Long doctorId = (doctorIdStr != null) ? Long.valueOf(doctorIdStr) : null;

        List<ConsultationRequest> consultationRequests = new ArrayList<ConsultationRequest>(
            consultationRequestRepository.findByStatusAndTypeAndSpecialityAndPatientIdAndOrganizationIdAndDoctorId(
                status, type, speciality, patientId, organizationId, doctorId));
        if (consultationRequests.isEmpty()) {
            throw new Exception("Consultation request(s) not found");
        }
        return consultationRequests;
    }

    @Override
    public ConsultationRequest get(Long consultationRequestId) throws Exception {
        Optional<ConsultationRequest> consultationRequest =
            consultationRequestRepository.findById(consultationRequestId);
        if (consultationRequest.isEmpty()) {
            throw new Exception("Consultation request not found");
        }
        return (consultationRequest.get());
    }

    @Override
    public Long close(Long consultationRequestId) throws Exception {
        Optional<ConsultationRequest> optionalConsultationRequest =
            consultationRequestRepository.findById(consultationRequestId);
        if (optionalConsultationRequest.isEmpty()) {
            throw new Exception("Consultation request not found");
        }
        ConsultationRequest consultationRequest = optionalConsultationRequest.get();
        consultationRequest.setStatus(CONSULTATION_REQUEST_STATUS.CLOSED);
        consultationRequestRepository.save(consultationRequest);
        return (consultationRequestId);
    }

    @Override
    public Long delete(Long consultationRequestId) throws Exception {
        Optional<ConsultationRequest> consultationRequest =
            consultationRequestRepository.findById(consultationRequestId);
        if (consultationRequest.isEmpty()) {
            throw new Exception("Consultation request not found");
        }
        consultationRequestRepository.deleteById(consultationRequestId);
        return (consultationRequestId);
    }
}
