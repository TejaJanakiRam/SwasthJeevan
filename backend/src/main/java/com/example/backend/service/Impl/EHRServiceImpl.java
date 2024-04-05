package com.example.backend.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.entity.EHR;
import com.example.backend.entity.EHR_TYPE;
import com.example.backend.repository.EHRRepository;
import com.example.backend.request.EHRMetadata;
import com.example.backend.service.EHRService;
import com.example.backend.types.DIAGNOSIS_TYPE;

@Service
public class EHRServiceImpl implements EHRService {

    @Autowired
    private EHRRepository ehrRepository;
    
    @Override
    public Long create(EHRMetadata ehrMetadata, MultipartFile document) throws Exception {
        EHR ehr = new EHR(ehrMetadata.getUserId(), ehrMetadata.getType(), ehrMetadata.getDiagnosisType(), ehrMetadata.getIssueDate(), ehrMetadata.getEndDate(), document.getBytes());
        Optional<EHR> ehrRec = ehrRepository.findByUserIdAndTypeAndDiagnosisTypeAndIssueDateAndEndDate(ehr.getUserId(), ehr.getType(), ehr.getDiagnosisType(), ehr.getIssueDate(), ehr.getEndDate());
        if (ehrRec.isPresent()) {
            throw new Exception("EHR already exists");
        }
        EHR savedEHR = ehrRepository.save(ehr);
        return savedEHR.getId();
    }

    @Override
    public List<EHRMetadata> list(String userIDStr, String typeStr, String diagnosisTypeStr, String fromDateStr, String toDateStr) throws Exception {
        Long userID = Long.valueOf(userIDStr);
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date fromDate = (fromDateStr != null) ? formatter.parse(fromDateStr) : null;
        Date toDate = (toDateStr != null) ? formatter.parse(toDateStr) : null;
        List<EHRMetadata> ehrsMetadata = new ArrayList<EHRMetadata>(ehrRepository.findByUserIdAndTypeAndDiagnosisTypeAndIssueDateGreaterThanAndIssueDateLessThan(userID, type, diagnosisType, fromDate, toDate));
        if (ehrsMetadata.isEmpty()) {
            throw new Exception("EHR(s) not found");
        }
        return ehrsMetadata;
    }

    @Override
    public EHR get(Long userID, Long EHRId) throws Exception {
        Optional<EHR> ehr = ehrRepository.findById(EHRId);
        if (ehr.isEmpty()) {
            throw new Exception("EHR not found");
        }
        return (ehr.get());
    }

    @Override
    public Long delete(Long userID, Long EHRId) throws Exception {
        Optional<EHR> ehr = ehrRepository.findById(EHRId);
        if (ehr.isEmpty()) {
            throw new Exception("EHR not found");
        }
        ehrRepository.deleteById(EHRId);
        return (EHRId);
    }

}
