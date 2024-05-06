package com.example.backend.dto;

import java.time.LocalDateTime;

import com.example.backend.types.DIAGNOSIS_TYPE;
import com.example.backend.types.DISEASE_TYPE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRecordDTO {
    private Long requestId;
    private Long patientId;
    private Long doctorId;
    private Long organizationId;
    private String symptoms;
    private DIAGNOSIS_TYPE diagnosis;
    private DISEASE_TYPE disease;
    private LocalDateTime issueDate;
    private LocalDateTime endDate;
    private String notes;
    private String medicines;
}
