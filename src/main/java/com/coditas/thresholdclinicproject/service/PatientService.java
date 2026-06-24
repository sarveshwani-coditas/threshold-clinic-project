package com.coditas.thresholdclinicproject.service;


import com.coditas.thresholdclinicproject.dto.patient.PatientRequest;
import com.coditas.thresholdclinicproject.dto.patient.PatientResponse;
import com.coditas.thresholdclinicproject.entity.Patient;
import com.coditas.thresholdclinicproject.mapper.PatientMapper;
import com.coditas.thresholdclinicproject.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientResponse registerPatient(PatientRequest request) {
        Patient patient = patientMapper.toEntity(request);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(savedPatient);
    }
}
