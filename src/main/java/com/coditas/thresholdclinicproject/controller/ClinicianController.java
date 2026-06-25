package com.coditas.thresholdclinicproject.controller;

import com.coditas.thresholdclinicproject.constants.ApiPaths;
import com.coditas.thresholdclinicproject.dto.ApplicationResponse;
import com.coditas.thresholdclinicproject.dto.ClinicianRequest;
import com.coditas.thresholdclinicproject.dto.ClinicianResponse;
import com.coditas.thresholdclinicproject.service.ClinicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.Clinician.BASE)
@RequiredArgsConstructor
public class ClinicianController {

    private final ClinicianService clinicianService;

    @PreAuthorize("hasRole('FRONT_DESK_COORDINATOR')")
    @PostMapping
    public ResponseEntity<ApplicationResponse<ClinicianResponse>> registerClinician(@Valid @RequestBody ClinicianRequest request){
        ClinicianResponse response = clinicianService.registerClinician(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApplicationResponse.<ClinicianResponse>builder()
                        .success(true)
                        .message("successfully registered a new Clinician")
                        .data(response)
                        .build()
        );
    }

}
