package com.example.backend.response;

import com.example.backend.entity.EHR;

import lombok.Data;

@Data
public class GetEHRResponse {
    EHR ehr;
    String message;
}
