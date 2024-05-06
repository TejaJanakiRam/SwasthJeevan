package com.example.backend.response;

import com.example.backend.entity.Consent;

import lombok.Data;

@Data
public class UpdateConsentResponse {
    Consent consent;
    String message;
}
