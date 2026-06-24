package com.coditas.thresholdclinicproject.controller;

import com.coditas.thresholdclinicproject.constants.ApiPaths;
import com.coditas.thresholdclinicproject.dto.ApplicationResponse;
import com.coditas.thresholdclinicproject.dto.patient.PatientRequest;
import com.coditas.thresholdclinicproject.dto.patient.PatientResponse;
import com.coditas.thresholdclinicproject.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.Patient.BASE)
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<ApplicationResponse<PatientResponse>> registerPatient(@RequestBody PatientRequest request){
        PatientResponse response = patientService.registerPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApplicationResponse.<PatientResponse>builder()
                        .success(true)
                        .message("You have been successfully registered")
                        .data(response)
                        .build()
        );
    }
}
