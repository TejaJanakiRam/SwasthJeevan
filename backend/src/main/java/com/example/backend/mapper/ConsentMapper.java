package com.example.backend.mapper;

import com.example.backend.dto.ConsentDTO;
import com.example.backend.entity.Consent;

public class ConsentMapper {
    public static Consent mapToConsent(ConsentDTO consentDTO) {
        Consent consent = new Consent(consentDTO.getType(), consentDTO.getPatientId(),
            consentDTO.getDoctorId(), consentDTO.getEhrId(), consentDTO.getStatus(),
            consentDTO.getStartDate(), consentDTO.getEndDate());
        return(consent);
    }

    public static ConsentDTO mapToConsentDTO(Consent consent) {
        ConsentDTO consentDTO = new ConsentDTO(consent.getType(),
            consent.getPatientId(), consent.getDoctorId(), consent.getEhrId(),
            consent.getStatus(), consent.getStartDate(), consent.getEndDate());
        return(consentDTO);
    }
}

