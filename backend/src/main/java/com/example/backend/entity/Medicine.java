package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
    MEDICINE_TYPE name;
    MEDICINE_FREQ frequency;
    Long duration;
    String notes;
}
