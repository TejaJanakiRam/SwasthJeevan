package com.example.backend.dto;

import java.time.LocalDateTime;

import com.example.backend.entity.CONSENT_STATUS;
import com.example.backend.entity.CONSENT_TYPE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsentDTO {
    private CONSENT_TYPE type = CONSENT_TYPE.LIST_ALL_EHR;
    private Long patientId;
    private Long doctorId;
    private Long ehrId;
    private CONSENT_STATUS status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
