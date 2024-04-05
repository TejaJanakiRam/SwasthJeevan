package com.example.backend.mapper;

import com.example.backend.dto.ConsentDTO;
import com.example.backend.entity.Consent;

public class ConsentMapper {
    public static Consent mapToUser(ConsentDTO consentDTO){
        Consent consent = new Consent(consentDTO.getSessionId(), consentDTO.getType(), consentDTO.getPatientId(), consentDTO.getDoctorId(), consentDTO.getEhrId(), consentDTO.getStatus());
        return(consent);
    }

    public static ConsentDTO mapToUser(Consent consent){
        ConsentDTO consentDTO = new ConsentDTO(consent.getSessionId(), consent.getType(), consent.getPatientId(), consent.getDoctorId(), consent.getEhrId(), consent.getStatus());
        return(consentDTO);
    }
}

