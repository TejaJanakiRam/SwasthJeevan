package com.example.backend.response;

import java.util.List;

import com.example.backend.request.EHRMetadata;

import lombok.Data;

@Data
public class ListEHRsResponse {
    List<EHRMetadata> ehrsMetadata;
    String message;
}
