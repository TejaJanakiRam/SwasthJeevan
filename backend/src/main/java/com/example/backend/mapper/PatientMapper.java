package com.example.backend.mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.backend.dto.PatientDTO;
import com.example.backend.entity.Patient;
import com.example.backend.entity.USER_GENDER;
import com.example.backend.entity.USER_TYPE;

@Component
public class PatientMapper {
    public Patient mapToPatient(Map<String, Object> requestBody) {
        Patient patient = new Patient();
        patient.setUsername((String) requestBody.get("username"));
        patient.setPassword((String) requestBody.get("password"));
        patient.setType(USER_TYPE.valueOf((String) requestBody.get("type")));
        patient.setEmail((String) requestBody.get("email"));
        patient.setPhone((String) requestBody.get("phone"));
        patient.setName((String) requestBody.get("name"));
        patient.setDob(Date.valueOf(LocalDate.parse((String) requestBody.get("dob"))));
        patient.setGender(USER_GENDER.valueOf((String) requestBody.get("gender")));
        patient.setAddress((String) requestBody.get("address"));
        return (patient);
    }

    public static PatientDTO mapToPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setUsername(patient.getUsername());
        patientDTO.setPassword(patient.getPassword());
        patientDTO.setType(patient.getType());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setPhone(patient.getPhone());
        patientDTO.setName(patient.getName());
        patientDTO.setDob(patient.getDob());
        patientDTO.setGender(patient.getGender());
        patientDTO.setAddress(patient.getAddress());
        return (patientDTO);
    }
}
