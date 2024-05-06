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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.ConsultationRecord;
import com.example.backend.service.ConsultationRecordService;
import com.example.backend.dto.ConsultationRecordDTO;
import com.example.backend.response.CreateConsultationRecordResponse;
import com.example.backend.response.DeleteConsultationRecordResponse;
import com.example.backend.response.ListConsultationRecordsResponse;
import com.example.backend.response.GetConsultationRecordResponse;
import com.example.backend.response.IssueConsultationRecordResponse;

@RestController
@RequestMapping("/api/consultation_record")
public class ConsultationRecordController {
    @Autowired
    private ConsultationRecordService consultationRecordService;

    @PostMapping("/")
    public ResponseEntity<CreateConsultationRecordResponse> createConsultationRecord(
            @RequestBody ConsultationRecordDTO consultationRecordDTO) throws Exception {
        CreateConsultationRecordResponse createConsultationRecordResponse = new CreateConsultationRecordResponse();
        HttpStatus httpStatus;
        Long createdConsultationRecordId = consultationRecordService.create(consultationRecordDTO);
        if (createdConsultationRecordId != null) {
            createConsultationRecordResponse.setConsultationRecordID(createdConsultationRecordId);
            createConsultationRecordResponse.setMessage("ConsultationRecord created successfully");
            httpStatus = HttpStatus.CREATED;
        } else {
            createConsultationRecordResponse.setMessage("ConsultationRecord creation failed");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return (new ResponseEntity<>(createConsultationRecordResponse, httpStatus));
    }

    @GetMapping("/")
    public ResponseEntity<ListConsultationRecordsResponse> listConsultationRecords(
            @RequestParam(required = false) String patientIDStr,
            @RequestParam(required = false) String doctorIDStr,
            @RequestParam(required = false) String organizationIDStr,
            @RequestParam(required = false) String issueDateStr,
            @RequestParam(required = false) String endDateStr) throws Exception {
        ListConsultationRecordsResponse listConsultationRecordsResponse = new ListConsultationRecordsResponse();
        HttpStatus httpStatus;
        List<ConsultationRecord> consultationRecords =
            new ArrayList<ConsultationRecord>(consultationRecordService.list(
                patientIDStr, doctorIDStr, organizationIDStr, issueDateStr, endDateStr));
        if (consultationRecords.isEmpty()) {
            listConsultationRecordsResponse.setMessage("Consultation records listing failed");
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            listConsultationRecordsResponse.setConsultationRecords(consultationRecords);
            listConsultationRecordsResponse.setMessage("Consultation records listed successfully");
            httpStatus = HttpStatus.OK;
        }
        return (new ResponseEntity<>(listConsultationRecordsResponse, httpStatus));
    }

    @GetMapping("/{consultationRecordID}")
    public ResponseEntity<GetConsultationRecordResponse> getConsultationRecord(
            @PathVariable("consultationRecordID") String consultationRecordIDStr) throws Exception {
        GetConsultationRecordResponse getConsultationRecordResponse = new GetConsultationRecordResponse();
        HttpStatus httpStatus;
        Long consultationRecordID = Long.valueOf(consultationRecordIDStr);
        ConsultationRecord consultationRecord = consultationRecordService.get(consultationRecordID);
        if (consultationRecord != null) {
            getConsultationRecordResponse.setConsultationRecord(consultationRecord);
            getConsultationRecordResponse.setMessage("Consultation record get successful");
            httpStatus = HttpStatus.OK;
        } else {
            getConsultationRecordResponse.setMessage("Consultation record get failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(getConsultationRecordResponse, httpStatus));
    }

    @PutMapping("/update/{consultationRecordID}")
    public ResponseEntity<IssueConsultationRecordResponse> issueConsultationRecord(
        @PathVariable("consultationRecordID") String consultationRecordIDStr,
        @RequestBody ConsultationRecordDTO consultationRecordDTO) throws Exception {
        Long consultationRecordID = Long.valueOf(consultationRecordIDStr);
        IssueConsultationRecordResponse issueConsultationRecordResponse = new IssueConsultationRecordResponse();
        HttpStatus httpStatus;
        Long issuedConsultationRecordID = consultationRecordService.update(
                                    consultationRecordID, consultationRecordDTO);
        if (issuedConsultationRecordID != null) {
            issueConsultationRecordResponse.setIssuedConsultationRecordID(issuedConsultationRecordID);
            issueConsultationRecordResponse.setMessage("ConsultationRecord created successfully");
            httpStatus = HttpStatus.CREATED;
        } else {
            issueConsultationRecordResponse.setMessage("ConsultationRecord creation failed");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return (new ResponseEntity<>(issueConsultationRecordResponse, httpStatus));
    }

    @DeleteMapping("/{consultationRecordID}")
    public ResponseEntity<DeleteConsultationRecordResponse> deleteConsultationRecord(
            @PathVariable("consultationRecordID") String consultationRecordIDStr) throws Exception {
        DeleteConsultationRecordResponse deleteConsultationRecordResponse = new DeleteConsultationRecordResponse();
        HttpStatus httpStatus;
        Long consultationRecordID = Long.valueOf(consultationRecordIDStr);
        Long deletedConsultationRecordId = consultationRecordService.delete(consultationRecordID);
        if (deletedConsultationRecordId != null) {
            deleteConsultationRecordResponse.setMessage("ConsultationRecord deleted successfully");
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            deleteConsultationRecordResponse.setMessage("ConsultationRecord delete failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(deleteConsultationRecordResponse, httpStatus));
    }
}
