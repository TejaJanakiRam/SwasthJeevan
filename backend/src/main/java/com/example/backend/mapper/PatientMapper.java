package com.example.backend.mapper;

import com.example.backend.dto.PatientDTO;
import com.example.backend.entity.Patient;

public class PatientMapper {
    public static Patient mapToPatient(PatientDTO patientDTO){
        Patient patient = new Patient(patientDTO.getId(), patientDTO.getUsername(), patientDTO.getPassword(), patientDTO.getType(),patientDTO.getEmail(),patientDTO.getPhone(), patientDTO.getName(), patientDTO.getDob(), patientDTO.getGender(), patientDTO.getAddress());
        return(patient);
    }

    public static PatientDTO mapToPatientDTO(Patient patient){
        PatientDTO patientDTO = new PatientDTO(patient.getId(), patient.getUsername(), patient.getPassword(), patient.getType(),patient.getEmail(), patient.getPhone(), patient.getName(), patient.getDob(), patient.getGender(), patient.getAddress());
        return(patientDTO);
    }
}
