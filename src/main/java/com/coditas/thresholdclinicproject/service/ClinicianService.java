package com.coditas.thresholdclinicproject.service;

import com.coditas.thresholdclinicproject.constants.ExceptionConstants;
import com.coditas.thresholdclinicproject.dto.ClinicianRequest;
import com.coditas.thresholdclinicproject.dto.ClinicianResponse;
import com.coditas.thresholdclinicproject.entity.Clinician;
import com.coditas.thresholdclinicproject.entity.User;
import com.coditas.thresholdclinicproject.enums.Role;
import com.coditas.thresholdclinicproject.exceptions.DuplicateResourceException;
import com.coditas.thresholdclinicproject.exceptions.ResourceNotFoundException;
import com.coditas.thresholdclinicproject.mapper.ClinicianMapper;
import com.coditas.thresholdclinicproject.repository.ClinicianRepository;
import com.coditas.thresholdclinicproject.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        if (userRepository.existsByEmail(request.getUser().getEmail())) {
            log.warn("Registration failed, user with email {} already exist", request.getUser().getEmail());
            throw new DuplicateResourceException(ExceptionConstants.DUPLICATE_RESOURCE);
        }

        Clinician clinician = new Clinician();
        clinician.setName(request.getName());
        clinician.setSpecialization(request.getSpecialization());
        clinician.setIsInactive(Boolean.FALSE);

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

    public Page<ClinicianResponse> getAllClinician(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        log.info("Successfully retrieved all clinicians ");
        return clinicianRepository
                .findAll(pageable)
                .map(clinicianMapper::toDTO);
    }

    public void deleteClinician(Integer clinicianId) {
        Clinician clinician = clinicianRepository.findById(clinicianId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.CLINICIAN_NOT_FOUND)
        );
        clinicianRepository.delete(clinician);
        log.info("a clinician with id {} successfully deleted ", clinicianId);
    }


    public ClinicianResponse updateClinician(Integer clinicianId, @Valid ClinicianRequest request) {
        Clinician clinician = clinicianRepository.findById(clinicianId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.CLINICIAN_NOT_FOUND)
        );

        if(request.getName()!= null){
            clinician.setName(request.getName());
        }
        if(request.getSpecialization()!=null){
            clinician.setSpecialization(request.getSpecialization());
        }

        Clinician savedClinician = clinicianRepository.save(clinician);

        return clinicianMapper.toDTO(savedClinician);

    }
}
