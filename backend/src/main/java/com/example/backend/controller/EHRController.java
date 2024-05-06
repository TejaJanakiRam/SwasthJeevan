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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.config.JwtService;
import com.example.backend.entity.EHR;
import com.example.backend.service.EHRService;
import com.example.backend.response.CreateEHRResponse;
import com.example.backend.response.DeleteEHRResponse;
import com.example.backend.request.EHRMetadata;
import com.example.backend.response.ListEHRsResponse;
import com.example.backend.response.GetEHRResponse;

@RestController
@RequestMapping("/api/ehr")
public class EHRController {
    @Autowired
    private EHRService ehrService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/")
    public ResponseEntity<CreateEHRResponse> createEHR(
        @RequestHeader("Authorization") String jwt,
        @RequestPart("ehrMetadata") EHRMetadata ehrMetadata,
        @RequestPart("document") MultipartFile document) throws Exception {

        String username = jwtService.extractUsername(jwt);
        CreateEHRResponse createEHRResponse = new CreateEHRResponse();
        HttpStatus httpStatus;

        Long createdEHRId = ehrService.create(username, ehrMetadata, document);
        if (createdEHRId != null) {
            createEHRResponse.setEhrID(createdEHRId);
            createEHRResponse.setMessage("EHR created successfully");
            httpStatus = HttpStatus.CREATED;
        } else {
            createEHRResponse.setMessage("EHR creation failed");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return (new ResponseEntity<>(createEHRResponse, httpStatus));
    }

    @GetMapping("/{userid}")
    public ResponseEntity<ListEHRsResponse> listEHRs(
        @RequestHeader("Authorization") String jwt,
        @PathVariable(name = "userid") String userIDStr,
        @RequestParam(required = false) String typeStr,
        @RequestParam(required = false) String diagnosisIDStr,
        @RequestParam(required = false) String fromDateStr,
        @RequestParam(required = false) String toDateStr) throws Exception {
        
        String username = jwtService.extractUsername(jwt);
        ListEHRsResponse listEHRsResponse = new ListEHRsResponse();
        HttpStatus httpStatus;
        List<EHRMetadata> ehrsMetadata = new ArrayList<EHRMetadata>(ehrService.list(
            username, userIDStr, typeStr, diagnosisIDStr, fromDateStr, toDateStr));
        if (ehrsMetadata.isEmpty()) {
            listEHRsResponse.setMessage("EHRs listing failed");
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            listEHRsResponse.setEhrsMetadata(ehrsMetadata);
            listEHRsResponse.setMessage("EHRs listed successfully");
            httpStatus = HttpStatus.OK;
        }
        return (new ResponseEntity<>(listEHRsResponse, httpStatus));
    }

    @GetMapping("/{userid}/{ehrid}")
    public ResponseEntity<GetEHRResponse> getEHR(
        @RequestHeader("Authorization") String jwt,
        @PathVariable("userid") String userIDStr,
        @PathVariable("ehrid") String ehrIDStr) throws Exception {

        String username = jwtService.extractUsername(jwt);
        GetEHRResponse getEHRResponse = new GetEHRResponse();
        HttpStatus httpStatus;
        Long userID = Long.valueOf(userIDStr);
        Long ehrID = Long.valueOf(ehrIDStr);
        EHR ehr = ehrService.get(username, userID, ehrID);
        if (ehr != null) {
            getEHRResponse.setEhr(ehr);
            getEHRResponse.setMessage("EHR get successful");
            httpStatus = HttpStatus.OK;
        } else {
            getEHRResponse.setMessage("EHR get failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(getEHRResponse, httpStatus));
    }

    @DeleteMapping("/{userid}/{ehrid}")
    public ResponseEntity<DeleteEHRResponse> deleteEHR(
        @RequestHeader("Authorization") String jwt,
        @PathVariable("userid") String userIDStr,
        @PathVariable("ehrid") String ehrIDStr) throws Exception {

        String username = jwtService.extractUsername(jwt);
        DeleteEHRResponse deleteEHRResponse = new DeleteEHRResponse();
        HttpStatus httpStatus;
        Long userID = Long.valueOf(userIDStr);
        Long ehrID = Long.valueOf(ehrIDStr);
        Long deletedEHRId = ehrService.delete(username, userID, ehrID);
        if (deletedEHRId != null) {
            deleteEHRResponse.setMessage("EHR deleted successfully");
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            deleteEHRResponse.setMessage("EHR delete failed");
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return (new ResponseEntity<>(deleteEHRResponse, httpStatus));
    }

}
