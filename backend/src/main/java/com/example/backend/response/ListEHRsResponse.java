package com.example.backend.response;

import java.util.List;

import com.example.backend.entity.EHR;

import lombok.Data;

@Data
public class ListEHRsResponse {
    List<EHR> ehrs;
    String message;
}
