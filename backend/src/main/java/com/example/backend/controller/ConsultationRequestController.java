package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.ConsultationRequest;
import com.example.backend.service.ConsultationRequestService;
import com.example.backend.dto.ConsultationRequestDTO;
import com.example.backend.response.CreateConsultationRequestResponse;
import com.example.backend.response.DeleteConsultationRequestResponse;
import com.example.backend.response.ListConsultationRequestsResponse;
import com.example.backend.response.GetConsultationRequestResponse;

@RestController
@RequestMapping("/api/consultation_request")
public class ConsultationRequestController {
    @Autowired
    private ConsultationRequestService consultationRequestService;

    @PostMapping("/")
    public ResponseEntity<CreateConsultationRequestResponse> createConsultationRequest(
            @RequestBody ConsultationRequestDTO consultationRequestDTO) throws Exception {
        CreateConsultationRequestResponse createConsultationRequestResponse = new CreateConsultationRequestResponse();
        HttpStatus httpStatus;
        Long createdConsultationRequestId = consultationRequestService.create(consultationRequestDTO);
        if (createdConsultationRequestId != null) {
            createConsultationRequestResponse.setConsultationRequestID(createdConsultationRequestId);
            createConsultationRequestResponse.setMessage("ConsultationRequest created successfully");
            httpStatus = HttpStatus.CREATED;
        } else {
            createConsultationRequestResponse.setMessage("ConsultationRequest creation failed");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return (new ResponseEntity<>(createConsultationRequestResponse, httpStatus));
    }

    @GetMapping("/")
    public ResponseEntity<ListConsultationRequestsResponse> listConsultationRequests(
            @PathVariable(required = false) String statusStr,
            @RequestParam(required = false) String typeStr,
            @RequestParam(required = false) String specialityStr,
            @RequestParam(required = false) String patientIdStr,
            @RequestParam(required = false) String organizationIdStr,
            @RequestParam(required = false) String doctorIdStr) throws Exception {
        ListConsultationRequestsResponse listConsultationRequestsResponse = new ListConsultationRequestsResponse();
        HttpStatus httpStatus;
        List<ConsultationRequest> consultationRequests =
            new ArrayList<ConsultationRequest>(consultationRequestService.list(
                statusStr, typeStr, specialityStr, patientIdStr, organizationIdStr, doctorIdStr));
        if (consultationRequests.isEmpty()) {
            listConsultationRequestsResponse.setMessage("Consultation requests listing failed");
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            listConsultationRequestsResponse.setConsultationRequests(consultationRequests);
            listConsultationRequestsResponse.setMessage("Consultation requests listed successfully");
            httpStatus = HttpStatus.OK;
        }
        return (new ResponseEntity<>(listConsultationRequestsResponse, httpStatus));
    }

    @GetMapping("/{consultationRequestID}")
    public ResponseEntity<GetConsultationRequestResponse> getConsultationRequest(
            @PathVariable("consultationRequestID") String consultationRequestIDStr) throws Exception {
        GetConsultationRequestResponse getConsultationRequestResponse = new GetConsultationRequestResponse();
        HttpStatus httpStatus;
        Long consultationRequestID = Long.valueOf(consultationRequestIDStr);
        ConsultationRequest consultationRequest = consultationRequestService.get(consultationRequestID);
        if (consultationRequest != null) {
            getConsultationRequestResponse.setConsultationRequest(consultationRequest);
            getConsultationRequestResponse.setMessage("Consultation request get successful");
            httpStatus = HttpStatus.OK;
        } else {
            getConsultationRequestResponse.setMessage("Consultation request get failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(getConsultationRequestResponse, httpStatus));
    }

    @DeleteMapping("/{consultationRequestID}")
    public ResponseEntity<DeleteConsultationRequestResponse> deleteConsultationRequest(
            @PathVariable("consultationRequestID") String consultationRequestIDStr) throws Exception {
        DeleteConsultationRequestResponse deleteConsultationRequestResponse = new DeleteConsultationRequestResponse();
        HttpStatus httpStatus;
        Long consultationRequestID = Long.valueOf(consultationRequestIDStr);
        Long deletedConsultationRequestId = consultationRequestService.delete(consultationRequestID);
        if (deletedConsultationRequestId != null) {
            deleteConsultationRequestResponse.setMessage("ConsultationRequest deleted successfully");
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            deleteConsultationRequestResponse.setMessage("ConsultationRequest delete failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(deleteConsultationRequestResponse, httpStatus));
    }

}
