package com.example.backend.controller;

import java.util.Collection;
import java.util.HashSet;
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
import com.example.backend.dto.PatientDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.entity.Patient;
import com.example.backend.entity.USER_TYPE;
import com.example.backend.entity.User;
import com.example.backend.mapper.PatientMapper;
import com.example.backend.mapper.UserMapper;
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
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/add")
    public ResponseEntity<PatientDTO> addPatient(@RequestBody PatientDTO patientDTO) throws Exception {
        Patient patient = PatientMapper.mapToPatient(patientDTO);
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        PatientDTO savedPatient =  PatientMapper.mapToPatientDTO(patientRepository.save(patient));
        return (new ResponseEntity<>(savedPatient, HttpStatus.OK));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUpUserHandler(@RequestBody UserDTO userDTO) throws Exception {
        User user = UserMapper.mapToUser(userDTO);
        User userExist = userRepository.findByUsername(user.getUsername());
        if (userExist != null) {
            throw new Exception("User exists");
        }
        User createdUser = new User();
        createdUser.setUsername(user.getUsername());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setType(user.getType());
        User savedUser = userRepository.save(createdUser);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(savedUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getUsername(),
                savedUser.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Success");
        authResponse.setType(user.getType());

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
