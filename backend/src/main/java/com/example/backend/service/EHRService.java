package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.entity.EHR;
import com.example.backend.request.EHRMetadata;

@Service
public interface EHRService {
    public Long create(String username, EHRMetadata ehrMetadata, MultipartFile document) throws Exception;
    public List<EHRMetadata> list(String username, String userIDStr, String typeStr, String diagnosisTypeStr, String fromDateStr, String toDateStr) throws Exception;
    public EHR get(String username, Long userID, Long ehrID) throws Exception;
    public Long delete(String username, Long userID, Long ehrID) throws Exception;
}
