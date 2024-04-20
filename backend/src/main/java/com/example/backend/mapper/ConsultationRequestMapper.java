package com.example.backend.mapper;

import com.example.backend.dto.ConsultationRequestDTO;
import com.example.backend.entity.ConsultationRequest;

public class ConsultationRequestMapper {

    public static ConsultationRequest mapToConsultationRequest(ConsultationRequestDTO consultationRequestDTO) {
        ConsultationRequest consultationRequest = new ConsultationRequest(
                                                  consultationRequestDTO.getType(),
                                                  consultationRequestDTO.getPatientId(),
                                                  consultationRequestDTO.getSpeciality(),
                                                  consultationRequestDTO.getOrganizationId(),
                                                  consultationRequestDTO.getDoctorId(),
                                                  consultationRequestDTO.getReferringOrganizationId(),
                                                  consultationRequestDTO.getReferringDoctorId(),
                                                  consultationRequestDTO.getStatus());
        return(consultationRequest);
    }

    public static ConsultationRequestDTO mapToConsultationRequestDTO(ConsultationRequest consultationRequest) {
        ConsultationRequestDTO consultationRequestDTO = new ConsultationRequestDTO(
                                                          consultationRequest.getType(),
                                                          consultationRequest.getPatientId(),
                                                          consultationRequest.getSpeciality(),
                                                          consultationRequest.getOrganizationId(),
                                                          consultationRequest.getDoctorId(),
                                                          consultationRequest.getReferringOrganizationId(),
                                                          consultationRequest.getReferringDoctorId(),
                                                          consultationRequest.getStatus());

        return(consultationRequestDTO);
    }
}

