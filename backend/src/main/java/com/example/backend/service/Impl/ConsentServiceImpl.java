package com.example.backend.service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.entity.Consent;
import com.example.backend.entity.User;
import com.example.backend.entity.CONSENT_STATUS;
import com.example.backend.entity.CONSENT_TYPE;
import com.example.backend.repository.ConsentRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.dto.ConsentDTO;
import com.example.backend.service.ConsentService;

@Service
public class ConsentServiceImpl implements ConsentService {

    @Autowired
    private ConsentRepository consentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long create(ConsentDTO consentDTO) throws Exception {
        Consent consent = new Consent(consentDTO.getType(), consentDTO.getPatientId(),
            consentDTO.getDoctorId(), consentDTO.getEhrId(), consentDTO.getStatus(),
            consentDTO.getStartDate(), null);
        Optional<Consent> consentRec =
            consentRepository.findByPatientIdAndDoctorIdAndTypeAndEhrId(
                consent.getPatientId(), consent.getDoctorId(),
                consent.getType(), consent.getEhrId());
        if (consentRec.isPresent()) {
            throw new Exception("Consent already exists");
        }
        Consent savedConsent = consentRepository.save(consent);
        return savedConsent.getId();
    }

    @Override
    public List<Consent> list(String userIDStr, String doctorIDStr, String typeStr,
        String ehrIDStr, String statusStr, String fromDateStr, String toDateStr) throws Exception {
        Long userID = (userIDStr != null) ? Long.valueOf(userIDStr) : null;
        Long doctorID = (doctorIDStr != null) ? Long.valueOf(doctorIDStr) : null;
        CONSENT_TYPE type;
        if (typeStr != null) {
            try {
                type = CONSENT_TYPE.valueOf(typeStr);
            } catch (Exception e) {
                type = null;
            }
        } else {
            type = null;
        }
        Long ehrID = (ehrIDStr != null) ? Long.valueOf(ehrIDStr) : null;
        CONSENT_STATUS status;
        if (statusStr != null) {
            try {
                status = CONSENT_STATUS.valueOf(statusStr);
            } catch (Exception e) {
                status = null;
            }
        } else {
            status = null;
        }
        LocalDateTime fromDate = (fromDateStr != null) ? LocalDateTime.parse(fromDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        LocalDateTime toDate = (toDateStr != null) ? LocalDateTime.parse(toDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        List<Consent> consents = new ArrayList<Consent>(
            consentRepository.findByTypeAndPatientIdAndDoctorIdAndEhrIdAndStatusAndStartTimeGreaterThanAndEndTimeLessThan(
                type, userID, doctorID, ehrID, status, fromDate, toDate));
        if (consents.isEmpty()) {
            throw new Exception("Consent(s) not found");
        }
        return consents;
    }

    @Override
    public Consent get(Long userID, Long ConsentId) throws Exception {
        Optional<Consent> Consent = consentRepository.findById(ConsentId);
        if (Consent.isEmpty()) {
            throw new Exception("Consent not found");
        }
        return (Consent.get());
    }

    @Override
    public Consent update(String username, Long userId, Long consentId, String statusStr, String endDateStr) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user.getId() != userId) {
            throw new Exception("Consent can only be provided or revoked by EHR owner");
        }
        CONSENT_STATUS status;
        if (statusStr != null) {
            try {
                status = CONSENT_STATUS.valueOf(statusStr);
            } catch (Exception e) {
                status = null;
            }
        } else {
            status = null;
        }
        LocalDateTime endDate = (endDateStr != null) ? LocalDateTime.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        if (status == null) {
            if (endDate == null)
                throw new Exception("Nothing to update");
        }
        Optional<Consent> consentOpt = consentRepository.findById(consentId);
        if (consentOpt.isEmpty()) {
            throw new Exception("Consent not found");
        }
        Consent consent = consentOpt.get();
        if (status != null)
            consent.setStatus(status);
        if(endDate != null)
            consent.setEndDate(endDate);
        consent.setStatus(status);
        Consent savedConsent = consentRepository.save(consent);
        return (savedConsent);
    }

    @Override
    public Long delete(Long userID, Long ConsentId) throws Exception {
        Optional<Consent> Consent = consentRepository.findById(ConsentId);
        if (Consent.isEmpty()) {
            throw new Exception("Consent not found");
        }
        consentRepository.deleteById(ConsentId);
        return (ConsentId);
    }
}
