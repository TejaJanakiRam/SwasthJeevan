package com.example.backend.response;

import java.util.List;

import com.example.backend.entity.Consent;

import lombok.Data;

@Data
public class ListConsentsResponse {
    List<Consent> consents;
    String message;
}
