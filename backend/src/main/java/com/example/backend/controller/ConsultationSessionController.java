package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.ConsultationSession;
import com.example.backend.service.ConsultationSessionService;
import com.example.backend.dto.ConsultationSessionDTO;
import com.example.backend.response.CreateConsultationSessionResponse;
import com.example.backend.response.GetConsultationSessionResponse;
import com.example.backend.response.ListConsultationSessionsResponse;
import com.example.backend.response.JoinConsultationSessionResponse;
import com.example.backend.response.LeaveConsultationSessionResponse;
import com.example.backend.response.CloseConsultationSessionResponse;

@RestController
@RequestMapping("/api/consultation_session")
public class ConsultationSessionController {
    @Autowired
    private ConsultationSessionService consultationSessionService;

    @PostMapping("/")
    public ResponseEntity<CreateConsultationSessionResponse> createConsultationSession(
            @RequestBody ConsultationSessionDTO consultationSessionDTO) throws Exception {
        CreateConsultationSessionResponse createConsultationSessionResponse = new CreateConsultationSessionResponse();
        HttpStatus httpStatus;
        Long createdConsultationSessionId = consultationSessionService.create(consultationSessionDTO);
        if (createdConsultationSessionId != null) {
            createConsultationSessionResponse.setConsultationSessionID(createdConsultationSessionId);
            createConsultationSessionResponse.setMessage("ConsultationSession created successfully");
            httpStatus = HttpStatus.CREATED;
        } else {
            createConsultationSessionResponse.setMessage("ConsultationSession creation failed");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return (new ResponseEntity<>(createConsultationSessionResponse, httpStatus));
    }

    @GetMapping("/list")
    public ResponseEntity<ListConsultationSessionsResponse> listConsultationSessions(
            @RequestParam(required = false) String idStr,
            @RequestParam(required = false) String consultationRequestIdStr,
            @RequestParam(required = false) String webrtcSessionIdStr,
            @RequestParam(required = false) String statusStr,
            @RequestParam(required = false) String closureReasonStr,
            @RequestParam(required = false) String typeStr,
            @RequestParam(required = false) String patientIdStr,
            @RequestParam(required = false) String doctorIdStr,
            @RequestParam(required = false) String organizationIdStr,
            @RequestParam(required = false) String supervisorDoctorIdStr) throws Exception {
        ListConsultationSessionsResponse listConsultationSessionsResponse = new ListConsultationSessionsResponse();
        HttpStatus httpStatus;
        List<ConsultationSession> consultationSessions =
            new ArrayList<ConsultationSession>(consultationSessionService.list(
                idStr, consultationRequestIdStr, webrtcSessionIdStr, statusStr, closureReasonStr,
                typeStr, patientIdStr, doctorIdStr, organizationIdStr, supervisorDoctorIdStr));
        if (consultationSessions.isEmpty()) {
            listConsultationSessionsResponse.setMessage("Consultation sessions listing failed");
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            listConsultationSessionsResponse.setConsultationSessions(consultationSessions);
            listConsultationSessionsResponse.setMessage("Consultation sessions listed successfully");
            httpStatus = HttpStatus.OK;
        }
        return (new ResponseEntity<>(listConsultationSessionsResponse, httpStatus));
    }

    @GetMapping("/{consultationSessionID}")
    public ResponseEntity<GetConsultationSessionResponse> getConsultationSession(
            @PathVariable("consultationSessionID") String consultationSessionIDStr) throws Exception {
        GetConsultationSessionResponse getConsultationSessionResponse = new GetConsultationSessionResponse();
        HttpStatus httpStatus;
        Long consultationSessionID = Long.valueOf(consultationSessionIDStr);
        ConsultationSession consultationSession = consultationSessionService.get(consultationSessionID);
        if (consultationSession != null) {
            getConsultationSessionResponse.setConsultationSession(consultationSession);
            getConsultationSessionResponse.setMessage("Consultation session get successful");
            httpStatus = HttpStatus.OK;
        } else {
            getConsultationSessionResponse.setMessage("Consultation session get failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(getConsultationSessionResponse, httpStatus));
    }

    @PutMapping("/join/{consultationSessionID}")
    public ResponseEntity<JoinConsultationSessionResponse> joinConsultationSession(
            @PathVariable("consultationSessionID") String consultationSessionIDStr,
            @RequestParam(required = true) String doctorIDStr) throws Exception {
        JoinConsultationSessionResponse joinConsultationSessionResponse = new JoinConsultationSessionResponse();
        HttpStatus httpStatus;
        Long consultationSessionID = Long.valueOf(consultationSessionIDStr);
        Long doctorID = Long.valueOf(doctorIDStr);
        ConsultationSession consultationSession = consultationSessionService.join(consultationSessionID, doctorID);
        if (consultationSession != null) {
            joinConsultationSessionResponse.setConsultationSession(consultationSession);
            joinConsultationSessionResponse.setMessage("Consultation session join successful");
            httpStatus = HttpStatus.OK;
        } else {
            joinConsultationSessionResponse.setMessage("Consultation session join failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(joinConsultationSessionResponse, httpStatus));
    }

    @PutMapping("/leave/{consultationSessionID}")
    public ResponseEntity<LeaveConsultationSessionResponse> leaveConsultationSession(
            @PathVariable("consultationSessionID") String consultationSessionIDStr,
            @RequestParam(required = true) String doctorIDStr) throws Exception {
        LeaveConsultationSessionResponse leaveConsultationSessionResponse = new LeaveConsultationSessionResponse();
        HttpStatus httpStatus;
        Long consultationSessionID = Long.valueOf(consultationSessionIDStr);
        Long doctorID = Long.valueOf(doctorIDStr);
        ConsultationSession consultationSession = consultationSessionService.leave(consultationSessionID, doctorID);
        if (consultationSession != null) {
            leaveConsultationSessionResponse.setConsultationSession(consultationSession);
            leaveConsultationSessionResponse.setMessage("Consultation session leave successful");
            httpStatus = HttpStatus.OK;
        } else {
            leaveConsultationSessionResponse.setMessage("Consultation session leave failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(leaveConsultationSessionResponse, httpStatus));
    }

    @PutMapping("/{consultationSessionID}")
    public ResponseEntity<CloseConsultationSessionResponse> closeConsultationSession(
            @PathVariable("consultationSessionID") String consultationSessionIDStr) throws Exception {
        CloseConsultationSessionResponse closeConsultationSessionResponse = new CloseConsultationSessionResponse();
        HttpStatus httpStatus;
        Long consultationSessionID = Long.valueOf(consultationSessionIDStr);
        Long closedConsultationSessionId = consultationSessionService.close(consultationSessionID);
        if (closedConsultationSessionId != null) {
            closeConsultationSessionResponse.setMessage("ConsultationSession closed successfully");
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            closeConsultationSessionResponse.setMessage("ConsultationSession close failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(closeConsultationSessionResponse, httpStatus));
    }
}
