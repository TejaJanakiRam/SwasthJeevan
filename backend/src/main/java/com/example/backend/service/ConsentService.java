package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.dto.ConsentDTO;
import com.example.backend.entity.Consent;

@Service
public interface ConsentService {
    public Long create(ConsentDTO consentDTO) throws Exception;
    public List<Consent> list(String userIDStr, String doctorIDStr,
        String typeStr, String ehrIDStr, String statusStr,
        String fromDateStr, String toDateStr) throws Exception;
    public Consent get(Long userId, Long consentId) throws Exception;
    public Consent update(String username, Long userId, Long consentId, String statusStr, String endDateStr) throws Exception;
    public Long delete(Long userId, Long consentId) throws Exception;
}
