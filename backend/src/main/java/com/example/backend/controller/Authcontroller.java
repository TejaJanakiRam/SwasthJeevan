package com.example.backend.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.config.JwtService;
import com.example.backend.dto.OrganizationDTO;
import com.example.backend.dto.PatientDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.entity.Doctor;
import com.example.backend.entity.Organization;
import com.example.backend.entity.Patient;
import com.example.backend.entity.USER_TYPE;
import com.example.backend.entity.User;
import com.example.backend.mapper.DoctorMapper;
import com.example.backend.mapper.OrganizationMapper;
import com.example.backend.mapper.PatientMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.repository.DoctorRepository;
import com.example.backend.repository.OrganizationRepository;
import com.example.backend.repository.PatientRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.request.LoginRequest;
import com.example.backend.response.AuthResponse;
import com.example.backend.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class Authcontroller {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @PostMapping("/add_org")
    public ResponseEntity<AuthResponse> addOrganization(@RequestBody Map<String,Object> request) throws Exception {
        Organization organization = organizationMapper.mapToOrganization(request);
        Organization organizationexist = organizationRepository.findByRegistrationNum(organization.getRegistrationNum());
        if(organizationexist != null){
            throw new Exception("Organization exits");
        }
        Organization savedOrganization = organizationRepository.save(organization);
        return (new ResponseEntity<>(HttpStatus.CREATED));

    }
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUpUserHandler(@RequestBody Map<String, Object> request) throws Exception {
        String role = (String) request.get("type");
        if (role == null) {
            throw new Exception("Role not present");
        }
        USER_TYPE type = USER_TYPE.valueOf(role);
        UserDetails userDetails = null;
        if (type == USER_TYPE.PATIENT) {
            Patient patient = patientMapper.mapToPatient(request);
            Patient patientExist = patientRepository.findByUsername(patient.getUsername());
            if (patientExist != null) {
                throw new Exception("Patient exists");
            }
            patient.setPassword(passwordEncoder.encode(patient.getPassword()));
            Patient savedPatient = patientRepository.save(patient);
            userDetails = customUserDetailsService.loadUserByUsername(savedPatient.getUsername());
        }

        else if(type == USER_TYPE.DOCTOR) {
            Doctor doctor = doctorMapper.mapToDoctor(request);
            Doctor doctorExist = doctorRepository.findByUsername(doctor.getUsername());
            if(doctorExist != null) {
                throw new Exception("Doctor exists");
            }
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
            Doctor savedDoctor = doctorRepository.save(doctor);
            userDetails = customUserDetailsService.loadUserByUsername(savedDoctor.getUsername());
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Success");
        String savedRole = userDetails.getAuthorities().isEmpty() ? ""
                : userDetails.getAuthorities().iterator().next().getAuthority();
        authResponse.setType(USER_TYPE.valueOf(savedRole));

        return (new ResponseEntity<>(authResponse, HttpStatus.CREATED));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signInUserHandler(@RequestBody UserDTO userDTO) throws Exception {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String type = userDTO.getType().toString();
        Authentication authentication = authenticate(username, password, type);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String actualType = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        String jwt = jwtService.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Success");
        authResponse.setType(USER_TYPE.valueOf(actualType));
        return (new ResponseEntity<>(authResponse, HttpStatus.OK));

    }

    private Authentication authenticate(String username, String password, String type) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        if (!type.equals(populateAuthorities(userDetails.getAuthorities()))) {
            throw new BadCredentialsException("Incorrect type");
        }
        return (new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()));
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auth = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            auth.add(authority.getAuthority());
        }
        return (String.join(",", auth));
    }
}
