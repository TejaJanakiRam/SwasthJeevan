package com.example.backend.response;

import java.util.List;

import com.example.backend.entity.ConsultationRequest;

import lombok.Data;

@Data
public class ListConsultationRequestsResponse {
    List<ConsultationRequest> consultationRequests;
    String message;
}
