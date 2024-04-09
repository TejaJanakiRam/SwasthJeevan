package com.example.backend.mapper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.backend.dto.DoctorDTO;
// import com.example.backend.dto.doctorDTO;

// import org.hibernate.mapping.Map;

import com.example.backend.entity.Doctor;
import com.example.backend.entity.Organization;
import com.example.backend.entity.Speciality;
import com.example.backend.entity.USER_GENDER;
import com.example.backend.entity.USER_TYPE;
import com.example.backend.repository.OrganizationRepository;
import com.example.backend.repository.SpecialityRepository;

@Component
public class DoctorMapper {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private SpecialityRepository specialityRepository;

    public Doctor mapToDoctor(Map<String, Object> requestBody) throws Exception {
        Doctor doctor = new Doctor();
        doctor.setUsername((String) requestBody.get("username"));
        doctor.setPassword((String) requestBody.get("password"));
        doctor.setType(USER_TYPE.valueOf((String) requestBody.get("type")));
        doctor.setEmail((String) requestBody.get("email"));
        doctor.setPhone((String) requestBody.get("phone"));
        doctor.setName((String) requestBody.get("name"));
        Organization organization = organizationRepository
                .findByRegistrationNum((String) requestBody.get("org_registration_num"));
        if (organization == null) {
            throw new Exception("Organization not there");
        }
        doctor.setOrganization(organization);
        Speciality speciality = specialityRepository.findBySpecialityCode((String) requestBody.get("speciality_code"));
        if (speciality == null) {
            throw new Exception("Specilaity not there");
        }
        doctor.setSpeciality(speciality);
        doctor.setGender(USER_GENDER.valueOf((String) requestBody.get("gender")));
        doctor.setRegistrationNum((String) requestBody.get("registration_num"));
        return (doctor);
    }

    public static DoctorDTO mapToDoctorDTO(Doctor doctor) {
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
        doctorDTO.setRegistrationNo(doctor.getRegistrationNum());
        return (doctorDTO);
    }
}
