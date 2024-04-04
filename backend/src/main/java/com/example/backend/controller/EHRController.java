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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.entity.EHR;
import com.example.backend.service.EHRService;
import com.example.backend.response.CreateEHRResponse;
import com.example.backend.response.DeleteEHRResponse;
import com.example.backend.request.CreateEHRRequest;
import com.example.backend.response.ListEHRsResponse;
import com.example.backend.response.GetEHRResponse;

@RestController
@RequestMapping("/api/ehr")
public class EHRController {
    @Autowired
    private EHRService ehrService;

    @PostMapping("/")
    public ResponseEntity<CreateEHRResponse> createEHR(@RequestPart("ehrMetadata") CreateEHRRequest ehrMetadata, @RequestPart("document") MultipartFile document) throws Exception {
        CreateEHRResponse createEHRResponse = new CreateEHRResponse();
        HttpStatus httpStatus;
        Long createdEHRId = ehrService.create(ehrMetadata, document);
        if (createdEHRId != null) {
            createEHRResponse.setEhrID(createdEHRId);
            createEHRResponse.setMessage("EHR created successfully");
            httpStatus = HttpStatus.CREATED;
        } else {
            createEHRResponse.setMessage("EHR creation failed");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return (new ResponseEntity<>(createEHRResponse, httpStatus));
    }

    @GetMapping("/{userid}")
    public ResponseEntity<ListEHRsResponse> listEHRs(@PathVariable(name = "userid") String userIDStr, @RequestParam(required = false) String typeStr, @RequestParam(required = false) String diagnosisIDStr, @RequestParam(required = false) String fromDateStr, @RequestParam(required = false) String toDateStr) throws Exception {
        ListEHRsResponse listEHRsResponse = new ListEHRsResponse();
        HttpStatus httpStatus;
        List<EHR> ehrs = new ArrayList<EHR>(ehrService.list(userIDStr, typeStr, diagnosisIDStr, fromDateStr, toDateStr));
        if (ehrs.isEmpty()) {
            listEHRsResponse.setMessage("EHRs listing failed");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            listEHRsResponse.setEhrs(ehrs);
            listEHRsResponse.setMessage("EHRs listed successfully");
            httpStatus = HttpStatus.OK;
        }
        return (new ResponseEntity<>(listEHRsResponse, httpStatus));
    }

    @GetMapping("/{userid}/{ehrid}")
    public ResponseEntity<GetEHRResponse> getEHR(@PathVariable("userid") String userIDStr, @PathVariable("ehrid") String ehrIDStr) throws Exception {
        GetEHRResponse getEHRResponse = new GetEHRResponse();
        HttpStatus httpStatus;
        Long userID = Long.valueOf(userIDStr);
        Long ehrID = Long.valueOf(ehrIDStr);
        EHR ehr = ehrService.get(userID, ehrID);
        if (ehr != null) {
            getEHRResponse.setEhr(ehr);
            getEHRResponse.setMessage("EHR get successful");
            httpStatus = HttpStatus.OK;
        } else {
            getEHRResponse.setMessage("EHR get failed");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return (new ResponseEntity<>(getEHRResponse, httpStatus));
    }

    @DeleteMapping("/{userid}/{ehrid}")
    public ResponseEntity<DeleteEHRResponse> deleteEHR(@PathVariable("userid") String userIDStr, @PathVariable("ehrid") String ehrIDStr) throws Exception {
        DeleteEHRResponse deleteEHRResponse = new DeleteEHRResponse();
        HttpStatus httpStatus;
        Long userID = Long.valueOf(userIDStr);
        Long ehrID = Long.valueOf(ehrIDStr);
        Long deletedEHRId = ehrService.delete(userID, ehrID);
        if (deletedEHRId != null) {
            deleteEHRResponse.setMessage("EHR deleted successfully");
            httpStatus = HttpStatus.OK;
        } else {
            deleteEHRResponse.setMessage("EHR delete failed");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return (new ResponseEntity<>(deleteEHRResponse, httpStatus));
    }

}
