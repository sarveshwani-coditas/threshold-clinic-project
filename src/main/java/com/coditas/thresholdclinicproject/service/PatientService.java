package com.coditas.thresholdclinicproject.service;


import com.coditas.thresholdclinicproject.constants.ExceptionConstants;
import com.coditas.thresholdclinicproject.dto.PatientDetailedResponse;
import com.coditas.thresholdclinicproject.dto.PatientRecordRequest;
import com.coditas.thresholdclinicproject.dto.patient.PatientRequest;
import com.coditas.thresholdclinicproject.dto.patient.PatientResponse;
import com.coditas.thresholdclinicproject.dto.users.UserResponse;
import com.coditas.thresholdclinicproject.entity.Patient;
import com.coditas.thresholdclinicproject.entity.PatientRecord;
import com.coditas.thresholdclinicproject.entity.User;
import com.coditas.thresholdclinicproject.enums.Role;
import com.coditas.thresholdclinicproject.exceptions.DuplicateResourceException;
import com.coditas.thresholdclinicproject.exceptions.ResourceNotFoundException;
import com.coditas.thresholdclinicproject.exceptions.UnAuthenticatedUserException;
import com.coditas.thresholdclinicproject.mapper.PatientMapper;
import com.coditas.thresholdclinicproject.mapper.PatientRecordMapper;
import com.coditas.thresholdclinicproject.repository.PatientRecordRepository;
import com.coditas.thresholdclinicproject.repository.PatientRepository;
import com.coditas.thresholdclinicproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientRecordMapper patientRecordMapper;
    private final PatientRecordRepository patientRecordRepository;
    private final PatientMapper patientMapper;
    private final MailService mailService;

    @Transactional
    public PatientResponse registerPatient(PatientRequest request) {

        if (userRepository.existsByEmail(request.getUser().getEmail())) {
            log.warn("Registration failed, user with email {} already exist", request.getUser().getEmail());
            throw new DuplicateResourceException(ExceptionConstants.DUPLICATE_RESOURCE);
        }

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

        mailService.sendEmail(
                savedPatient.getUser().getEmail(),
                "Registration Successful",
                "You have been successfully registered as patient"
        );

        UserResponse userResponse = UserResponse.builder()
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .createdAt(savedUser.getCreatedAt())
                .build();

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setId(savedPatient.getId());
        patientResponse.setName(savedPatient.getName());
        patientResponse.setUser(userResponse);

        log.info("Successfully registered patient with id {} ", savedPatient.getId());
        return patientResponse;
    }

    @Transactional
    public PatientDetailedResponse fillPatientRecord(Integer patientId, PatientRecordRequest request) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.PATIENT_NOT_FOUND)
        );

        PatientRecord patientRecord = patientRecordMapper.toEntity(request);
        patientRecord.setCreatedAt(Instant.now());
        patientRecord.setUpdatedAt(Instant.now());

        PatientRecord savedPatientRecord = patientRecordRepository.save(patientRecord);
        patient.setPatientRecord(savedPatientRecord);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDetailedDTO(savedPatient);

    }

    public PatientDetailedResponse getPatientInfo(Integer patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.PATIENT_NOT_FOUND)
        );

        return patientMapper.toDetailedDTO(patient);
    }

    public PatientResponse getPatientInfoByAdmin(Integer patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.PATIENT_NOT_FOUND)
        );

        return patientMapper.toDTO(patient);
    }

    public PatientDetailedResponse getPatientInfoAsPatient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UnAuthenticatedUserException(ExceptionConstants.UNAUTHENTICATED_USER);
        }
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.RESOURCE_NOT_FOUND)
        );
        Patient patient = patientRepository.findByUser(user);
        return patientMapper.toDetailedDTO(patient);
    }
}
