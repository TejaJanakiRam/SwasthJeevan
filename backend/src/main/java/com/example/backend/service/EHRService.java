package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.entity.EHR;
import com.example.backend.request.EHRMetadata;

@Service
public interface EHRService {
    public Long create(EHRMetadata ehrMetadata, MultipartFile document) throws Exception;
    public List<EHRMetadata> list(String userIDStr, String typeStr, String diagnosisTypeStr, String fromDateStr, String toDateStr) throws Exception;
    public EHR get(Long userID, Long EHRId) throws Exception;
    public Long delete(Long userID, Long EHRId) throws Exception;
}
