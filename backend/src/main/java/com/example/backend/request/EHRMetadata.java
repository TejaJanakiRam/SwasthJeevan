package com.example.backend.request;

import java.time.LocalDateTime;

import com.example.backend.entity.EHR_TYPE;
import com.example.backend.types.DIAGNOSIS_TYPE;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EHRMetadata {
    private Long id = null;
    private Long userId;
    private EHR_TYPE type;
    private DIAGNOSIS_TYPE diagnosisType;
    private LocalDateTime issueDate;
    private LocalDateTime endDate;
}
