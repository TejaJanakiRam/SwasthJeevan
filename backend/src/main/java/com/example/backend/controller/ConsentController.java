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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Consent;
import com.example.backend.service.ConsentService;
import com.example.backend.config.JwtService;
import com.example.backend.dto.ConsentDTO;
import com.example.backend.response.CreateConsentResponse;
import com.example.backend.response.UpdateConsentResponse;
import com.example.backend.response.GetConsentResponse;
import com.example.backend.response.ListConsentsResponse;
import com.example.backend.response.DeleteConsentResponse;

@RestController
@RequestMapping("/api/consent")
public class ConsentController {
    @Autowired
    private ConsentService consentService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/")
    public ResponseEntity<CreateConsentResponse> createConsent(
        @RequestBody ConsentDTO consentDTO) throws Exception {
        CreateConsentResponse createConsentResponse = new CreateConsentResponse();
        HttpStatus httpStatus;
        Long createdConsentId = consentService.create(consentDTO);
        if (createdConsentId != null) {
            createConsentResponse.setConsentID(createdConsentId);
            createConsentResponse.setMessage("Consent created successfully");
            httpStatus = HttpStatus.CREATED;
        } else {
            createConsentResponse.setMessage("Consent creation failed");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return (new ResponseEntity<>(createConsentResponse, httpStatus));
    }

    @GetMapping("/{userid}")
    public ResponseEntity<ListConsentsResponse> listConsents(
        @PathVariable(name = "userid") String userIDStr,
        @RequestParam(required = false) String doctorIDStr,
        @RequestParam(required = false) String typeStr,
        @RequestParam(required = false) String ehrIDStr,
        @RequestParam(required = false) String statusStr,
        @RequestParam(required = false) String fromDateStr,
        @RequestParam(required = false) String toDateStr) throws Exception {
        ListConsentsResponse listConsentsResponse = new ListConsentsResponse();
        HttpStatus httpStatus;
        List<Consent> consents = new ArrayList<Consent>(consentService.list(
            userIDStr, doctorIDStr, typeStr,
            ehrIDStr, statusStr, fromDateStr, toDateStr));
        if (consents.isEmpty()) {
            listConsentsResponse.setMessage("Consents listing failed");
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            listConsentsResponse.setConsents(consents);
            listConsentsResponse.setMessage("Consents listed successfully");
            httpStatus = HttpStatus.OK;
        }
        return (new ResponseEntity<>(listConsentsResponse, httpStatus));
    }

    @GetMapping("/{userid}/{Consentid}")
    public ResponseEntity<GetConsentResponse> getConsent(
        @PathVariable("userid") String userIDStr,
        @PathVariable("Consentid") String ConsentIDStr) throws Exception {
        GetConsentResponse getConsentResponse = new GetConsentResponse();
        HttpStatus httpStatus;
        Long userID = Long.valueOf(userIDStr);
        Long ConsentID = Long.valueOf(ConsentIDStr);
        Consent Consent = consentService.get(userID, ConsentID);
        if (Consent != null) {
            getConsentResponse.setConsent(Consent);
            getConsentResponse.setMessage("Consent get successful");
            httpStatus = HttpStatus.OK;
        } else {
            getConsentResponse.setMessage("Consent get failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(getConsentResponse, httpStatus));
    }


    @PutMapping("/{userid}/{consentid}")
    public ResponseEntity<UpdateConsentResponse> updateConsent(
        @RequestHeader("Authorization") String jwt,
        @PathVariable("userid") String userIDStr,
        @PathVariable("consentid") String consentIDStr,
        @RequestParam(required = false) String statusStr,
        @RequestParam(required = false) String endDateStr) throws Exception {
        
        String username = jwtService.extractUsername(jwt);
        UpdateConsentResponse updateConsentResponse = new UpdateConsentResponse();
        HttpStatus httpStatus;
        Long userID = Long.valueOf(userIDStr);
        Long consentID = Long.valueOf(consentIDStr);
        Consent Consent = consentService.update(username, userID, consentID, statusStr, endDateStr);
        if (Consent != null) {
            updateConsentResponse.setConsent(Consent);
            updateConsentResponse.setMessage("Consent update successful");
            httpStatus = HttpStatus.OK;
        } else {
            updateConsentResponse.setMessage("Consent get failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(updateConsentResponse, httpStatus));
    }

    @DeleteMapping("/{userid}/{Consentid}")
    public ResponseEntity<DeleteConsentResponse> deleteConsent(
        @PathVariable("userid") String userIDStr,
        @PathVariable("Consentid") String ConsentIDStr) throws Exception {
        DeleteConsentResponse deleteConsentResponse = new DeleteConsentResponse();
        HttpStatus httpStatus;
        Long userID = Long.valueOf(userIDStr);
        Long ConsentID = Long.valueOf(ConsentIDStr);
        Long deletedConsentId = consentService.delete(userID, ConsentID);
        if (deletedConsentId != null) {
            deleteConsentResponse.setMessage("Consent deleted successfully");
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            deleteConsentResponse.setMessage("Consent delete failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(deleteConsentResponse, httpStatus));
    }

}
