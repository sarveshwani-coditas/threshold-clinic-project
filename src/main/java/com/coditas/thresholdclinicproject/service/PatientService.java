package com.coditas.thresholdclinicproject.service;


import com.coditas.thresholdclinicproject.dto.patient.PatientRequest;
import com.coditas.thresholdclinicproject.dto.patient.PatientResponse;
import com.coditas.thresholdclinicproject.dto.users.UserResponse;
import com.coditas.thresholdclinicproject.entity.Patient;
import com.coditas.thresholdclinicproject.entity.User;
import com.coditas.thresholdclinicproject.enums.Role;
import com.coditas.thresholdclinicproject.repository.PatientRepository;
import com.coditas.thresholdclinicproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Transactional
    public PatientResponse registerPatient(PatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.getName());

        User user = new User();
        user.setRole(Role.PATIENT);
        user.setPassword(passwordEncoder.encode(request.getUser().getPassword()));
        user.setEmail(request.getUser().getEmail());
        user.setUsername(request.getUser().getUsername());
        user.setCreatedAt(Instant.now());

        User savedUser = userRepository.save(user);
        patient.setUser(savedUser);
        Patient savedPatient = patientRepository.save(patient);

        UserResponse userResponse = UserResponse.builder()
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .createdAt(savedUser.getCreatedAt())
                .build();

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setName(savedPatient.getName());
        patientResponse.setUser(userResponse);

        return patientResponse;
    }
}
