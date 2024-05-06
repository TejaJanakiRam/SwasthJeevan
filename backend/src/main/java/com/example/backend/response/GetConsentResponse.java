package com.example.backend.response;

import com.example.backend.entity.Consent;

import lombok.Data;

@Data
public class GetConsentResponse {
    Consent consent;
    String message;
}
