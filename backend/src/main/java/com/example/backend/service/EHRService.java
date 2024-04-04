package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.entity.EHR;
import com.example.backend.request.CreateEHRRequest;

@Service
public interface EHRService {
    public Long create(CreateEHRRequest ehrMetadata, MultipartFile document) throws Exception;
    public List<EHR> list(String userIDStr, String typeStr, String diagnosisIDStr, String fromDateStr, String toDateStr) throws Exception;
    public EHR get(Long userID, Long EHRId) throws Exception;
    public Long delete(Long userID, Long EHRId) throws Exception;
}
