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
import com.example.backend.request.CreateEHRRequest;
import com.example.backend.service.EHRService;

@Service
public class EHRServiceImpl implements EHRService {

    @Autowired
    private EHRRepository ehrRepository;
    
    @Override
    public Long create(CreateEHRRequest ehrMetadata, MultipartFile document) throws Exception {
        EHR ehr = new EHR(ehrMetadata.getUserId(), ehrMetadata.getType(), ehrMetadata.getDiagnosisId(), ehrMetadata.getIssueDate(), ehrMetadata.getEndDate(), document.getBytes());
        Optional<EHR> ehrRec = ehrRepository.findByUserIdAndTypeAndDiagnosisIdAndIssueDateAndEndDate(ehr.getUserId(), ehr.getType(), ehr.getDiagnosisId(), ehr.getIssueDate(), ehr.getEndDate());
        if (ehrRec.isPresent()) {
            throw new Exception("EHR already exists");
        }
        EHR savedEHR = ehrRepository.save(ehr);
        return savedEHR.getId();
    }

    @Override
    public List<EHR> list(String userIDStr, String typeStr, String diagnosisIDStr, String fromDateStr, String toDateStr) throws Exception {
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
        Long diagnosisID = (diagnosisIDStr != null) ? Long.valueOf(diagnosisIDStr) : null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date fromDate = (fromDateStr != null) ? formatter.parse(fromDateStr) : null;
        Date toDate = (toDateStr != null) ? formatter.parse(toDateStr) : null;
        List<EHR> ehrs = new ArrayList<EHR>(ehrRepository.findByUserIdAndTypeAndDiagnosisIdAndIssueDateGreaterThanEqualAndIssueDateLessThanEqual(userID, type, diagnosisID, fromDate, toDate));
        if (ehrs.isEmpty()) {
            throw new Exception("EHR(s) not found");
        }
        return ehrs;
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
