package com.example.backend.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnTransformer;

import com.example.backend.types.DIAGNOSIS_TYPE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

    @JoinColumn(name = "user_id", referencedColumnName="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, optional=false)
    @Transient
    private User user;
    @Column(name = "user_id")
    private Long userId;

    private EHR_TYPE type = EHR_TYPE.CONSULTATION;
    private DIAGNOSIS_TYPE diagnosisType;
    private LocalDateTime issueDate;
    private LocalDateTime endDate;
    @Lob
    @ColumnTransformer(
        read = "AES_DECRYPT(document, 'mySecretKey')",
        write = "AES_ENCRYPT(?, 'mySecretKey')"
    )
    @Column(name = "document", columnDefinition="BLOB")
    private byte[] document;

    public EHR(Long userId, EHR_TYPE type, DIAGNOSIS_TYPE diagnosisType, LocalDateTime issueDate, LocalDateTime endDate, byte[] document)
    {
        this.userId = userId;
        this.type = type;
        this.diagnosisType = diagnosisType;
        this.issueDate = issueDate;
        this.endDate = endDate;
        this.document = document;
    }
}
