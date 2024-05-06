package com.example.backend.service.Impl;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.entity.CONSENT_STATUS;
import com.example.backend.entity.CONSENT_TYPE;
import com.example.backend.entity.Consent;
import com.example.backend.entity.EHR;
import com.example.backend.entity.EHR_TYPE;
import com.example.backend.entity.USER_TYPE;
import com.example.backend.entity.User;
import com.example.backend.repository.ConsentRepository;
import com.example.backend.repository.EHRRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.request.EHRMetadata;
import com.example.backend.service.EHRService;
import com.example.backend.types.DIAGNOSIS_TYPE;

@Service
public class EHRServiceImpl implements EHRService {

    @Autowired
    private EHRRepository ehrRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConsentRepository consentRepository;
    
    private boolean isAuthorized(String username, Long userID, CONSENT_TYPE type)
    {
        boolean userIsAuthorized = true;
        User user = userRepository.findByUsername(username);
        if (user.getId() != userID)
        {
            USER_TYPE usertype = user.getType();
            Optional<Consent> consentOpt =
                consentRepository.findByPatientIdAndDoctorIdAndTypeAndEhrId(
                    userID, user.getId(), type, null);
            if ((usertype != USER_TYPE.DOCTOR) || (consentOpt.isEmpty())) {
                userIsAuthorized = false;
            }
            Consent consent = null;
            consent = consentOpt.get();
            LocalDateTime nowdt = LocalDateTime.now();
            if (nowdt.isBefore(consent.getStartDate()) ||
                nowdt.isAfter(consent.getEndDate()) ||
                (consent.getStatus() != CONSENT_STATUS.PROVIDED)) {
                    userIsAuthorized = false;
            }
        }
        return userIsAuthorized;
    }

    @Override
    public Long create(String username, EHRMetadata ehrMetadata,
        MultipartFile document) throws Exception {
        
        User user = userRepository.findByUsername(username);
        if (user.getId() != ehrMetadata.getUserId()) {
            throw new Exception("EHR can only be created by EHR owner");
        }
        EHR ehr = new EHR(ehrMetadata.getUserId(), ehrMetadata.getType(), ehrMetadata.getDiagnosisType(), ehrMetadata.getIssueDate(), ehrMetadata.getEndDate(), document.getBytes());
        Optional<EHR> ehrRec = ehrRepository.findByUserIdAndTypeAndDiagnosisTypeAndIssueDateAndEndDate(ehr.getUserId(), ehr.getType(), ehr.getDiagnosisType(), ehr.getIssueDate(), ehr.getEndDate());
        if (ehrRec.isPresent()) {
            throw new Exception("EHR already exists");
        }
        EHR savedEHR = ehrRepository.save(ehr);
        return savedEHR.getId();
    }

    @Override
    public List<EHRMetadata> list(String username, String userIDStr, String typeStr,
        String diagnosisTypeStr, String fromDateStr, String toDateStr) throws Exception {
        
        Long userID = Long.valueOf(userIDStr);
        if (!isAuthorized(username, userID, CONSENT_TYPE.LIST_ALL_EHR))
        {
            throw new Exception("EHR(s) can only be listed by EHR owner or consulting doctor with consent");
        }
        EHR_TYPE type;
        if (typeStr != null) {
            try {
                type = EHR_TYPE.valueOf(typeStr);
            } catch (Exception e) {
                type = null;
            }
        } else {
            type = null;
        }
        DIAGNOSIS_TYPE diagnosisType;
        if (diagnosisTypeStr != null) {
            try {
                diagnosisType = DIAGNOSIS_TYPE.valueOf(diagnosisTypeStr);
            } catch (Exception e) {
                diagnosisType = null;
            }
        } else {
            diagnosisType = null;
        }
        LocalDateTime fromDate = (fromDateStr != null) ? LocalDateTime.parse(fromDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        LocalDateTime toDate = (toDateStr != null) ? LocalDateTime.parse(toDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        List<EHRMetadata> ehrsMetadata = new ArrayList<EHRMetadata>(ehrRepository.findByUserIdAndTypeAndDiagnosisTypeAndIssueDateGreaterThanAndIssueDateLessThan(userID, type, diagnosisType, fromDate, toDate));
        if (ehrsMetadata.isEmpty()) {
            throw new Exception("EHR(s) not found");
        }
        return ehrsMetadata;
    }

    @Override
    public EHR get(String username, Long userID, Long ehrID) throws Exception {
        if (!isAuthorized(username, userID, CONSENT_TYPE.LIST_ALL_EHR))
        {
            throw new Exception("EHR can only be viewed by EHR owner or consulting doctor with consent");
        }
        Optional<EHR> ehr = ehrRepository.findById(ehrID);
        if (ehr.isEmpty()) {
            throw new Exception("EHR not found");
        }
        return (ehr.get());
    }

    @Override
    public Long delete(String username, Long userID, Long ehrID) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user.getId() != userID) {
            throw new Exception("EHR can only be deleted by EHR owner");
        }
        Optional<EHR> ehr = ehrRepository.findById(ehrID);
        if (ehr.isEmpty()) {
            throw new Exception("EHR not found");
        }
        ehrRepository.deleteById(ehrID);
        return (ehrID);
    }
}
