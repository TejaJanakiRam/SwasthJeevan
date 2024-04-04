package com.example.backend.mapper;

import java.util.Map;

import com.example.backend.dto.DoctorDTO;
// import com.example.backend.dto.doctorDTO;

// import org.hibernate.mapping.Map;

import com.example.backend.entity.Doctor;
import com.example.backend.entity.Organization;
import com.example.backend.entity.Speciality;
import com.example.backend.entity.USER_GENDER;
import com.example.backend.entity.USER_TYPE;

public class DoctorMapper {
    public static Doctor mapToDoctor(Map<String ,Object> requestBody){
        Doctor doctor = new Doctor();
        doctor.setUsername((String) requestBody.get("username"));
        doctor.setPassword((String) requestBody.get("password"));
        doctor.setType(USER_TYPE.valueOf((String) requestBody.get("type")));
        doctor.setEmail((String) requestBody.get("email"));
        doctor.setPhone((String) requestBody.get("phone"));
        doctor.setName((String) requestBody.get("name"));
        // doctor.setorg((Long) requestBody.get("orgId"));
        // doctor.setSpecId((Long) requestBody.get("specId"));
        Organization organization = new Organization();
        organization.setId((Integer) ((Map<String, Object>) requestBody.get("organization")).get("id"));
        doctor.setOrganization(organization);

        Speciality speciality = new Speciality();
        speciality.setId((Integer) ((Map<String, Object>) requestBody.get("speciality")).get("id"));
        doctor.setSpeciality(speciality);
        // doctor.setOrganization((Organization) requestBody.get("organization"));
        // doctor.setSpeciality((Speciality) requestBody.get("speciality"));
        doctor.setGender(USER_GENDER.valueOf((String) requestBody.get("gender")));
        doctor.setRegistrationNo((String) requestBody.get("registrationNo"));
        return (doctor);
    }

    public static DoctorDTO matToDoctorDTO(Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setUsername(doctor.getUsername());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setType(doctor.getType());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setPhone(doctor.getPhone());
        doctorDTO.setName(doctor.getName());
        // doctorDTO.setOrgId(doctor.getOrgId());
        // doctorDTO.setSpecId(doctor.getSpecId());
        doctorDTO.setOrganization(doctor.getOrganization());
        doctorDTO.setSpeciality(doctor.getSpeciality());
        doctorDTO.setGender(doctor.getGender());
        doctorDTO.setRegistrationNo(doctor.getRegistrationNo());
        return (doctorDTO);
    }
}
