package com.coditas.thresholdclinicproject.service;

import com.coditas.thresholdclinicproject.constants.ExceptionConstants;
import com.coditas.thresholdclinicproject.dto.ClinicianRequest;
import com.coditas.thresholdclinicproject.dto.ClinicianResponse;
import com.coditas.thresholdclinicproject.entity.Clinician;
import com.coditas.thresholdclinicproject.entity.User;
import com.coditas.thresholdclinicproject.enums.Role;
import com.coditas.thresholdclinicproject.exceptions.DuplicateResourceException;
import com.coditas.thresholdclinicproject.mapper.ClinicianMapper;
import com.coditas.thresholdclinicproject.repository.ClinicianRepository;
import com.coditas.thresholdclinicproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClinicianService {

    private final ClinicianRepository clinicianRepository;
    private final ClinicianMapper clinicianMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public ClinicianResponse registerClinician(ClinicianRequest request) {

        if(userRepository.existsByEmail(request.getUser().getEmail())){
            log.warn("Registration failed, user with email {} already exist", request.getUser().getEmail());
            throw new DuplicateResourceException(ExceptionConstants.DUPLICATE_RESOURCE);
        }

        Clinician clinician = new Clinician();
        clinician.setName(request.getName());
        clinician.setSpecialization(request.getSpecialization());

        User user = new User();
        user.setRole(Role.CLINICIAN);
        user.setPassword(passwordEncoder.encode(request.getUser().getPassword()));
        user.setEmail(request.getUser().getEmail());
        user.setUsername(request.getUser().getUsername());
        user.setCreatedAt(Instant.now());

        User savedUser = userRepository.save(user);
        clinician.setUser(savedUser);
        Clinician savedClinician = clinicianRepository.save(clinician);

        log.info("Successfully registered clinician with id {} ", savedClinician.getId());
        return clinicianMapper.toDTO(savedClinician);
    }
}
