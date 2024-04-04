package com.example.backend.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ehr")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class EHR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private EHR_TYPE type = EHR_TYPE.CONSULTATION;
    private Long diagnosisId;
    private Date issueDate;
    private Date endDate;
    @Lob
    @Column(name = "document", columnDefinition="BLOB")
    private byte[] document;

    public EHR(Long userId, EHR_TYPE type, Long diagnosisId, Date issueDate, Date endDate, byte[] document)
    {
        this.userId = userId;
        this.type = type;
        this.diagnosisId = diagnosisId;
        this.issueDate = issueDate;
        this.endDate = endDate;
        this.document = document;
    }
}
