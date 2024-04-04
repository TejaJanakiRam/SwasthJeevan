package com.example.backend.request;

import java.util.Date;

import com.example.backend.entity.EHR_TYPE;

import lombok.Data;

@Data
public class CreateEHRRequest {
    private Long userId;
    private EHR_TYPE type;
    private Long diagnosisId;
    private Date issueDate;
    private Date endDate;
}
