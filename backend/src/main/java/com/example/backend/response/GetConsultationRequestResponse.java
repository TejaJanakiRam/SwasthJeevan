package com.example.backend.response;

import com.example.backend.entity.ConsultationRequest;

import lombok.Data;

@Data
public class GetConsultationRequestResponse {
    ConsultationRequest consultationRequest;
    String message;
}
