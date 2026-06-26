package com.coditas.thresholdclinicproject.controller;

import com.coditas.thresholdclinicproject.constants.ApiPaths;
import com.coditas.thresholdclinicproject.dto.ApplicationResponse;
import com.coditas.thresholdclinicproject.dto.ClinicianRequest;
import com.coditas.thresholdclinicproject.dto.ClinicianResponse;
import com.coditas.thresholdclinicproject.service.ClinicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.BASE_PATH)
@RequiredArgsConstructor
public class ClinicianController {

    private final ClinicianService clinicianService;

    @PreAuthorize("hasRole('FRONT_DESK_COORDINATOR')")
    @PostMapping(ApiPaths.Clinician.CLINICIAN)
    public ResponseEntity<ApplicationResponse<ClinicianResponse>> registerClinician(@Valid @RequestBody ClinicianRequest request) {
        ClinicianResponse response = clinicianService.registerClinician(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApplicationResponse.<ClinicianResponse>builder()
                        .success(true)
                        .message("successfully registered a new Clinician")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('FRONT_DESK_COORDINATOR', 'PATIENT', 'CLINICIAN')")
    @GetMapping(ApiPaths.Clinician.CLINICIAN)
    public ResponseEntity<ApplicationResponse<Page<ClinicianResponse>>> getAllClinician(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<ClinicianResponse> response = clinicianService.getAllClinician(page, size);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<Page<ClinicianResponse>>builder()
                        .success(true)
                        .message("successfully registered a new Clinician")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasRole('FRONT_DESK_COORDINATOR')")
    @PatchMapping(ApiPaths.Admin.ADMIN + ApiPaths.Clinician.CLINICIAN+ ApiPaths.Clinician.ID)
    public ResponseEntity<ApplicationResponse<ClinicianResponse>> updateClinician(@PathVariable(name = "id") Integer clinicianId, @Valid @RequestBody ClinicianRequest request) {
        ClinicianResponse response = clinicianService.updateClinician(clinicianId, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<ClinicianResponse>builder()
                        .success(true)
                        .message("successfully updated a Clinician's record")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasRole('FRONT_DESK_COORDINATOR')")
    @DeleteMapping(ApiPaths.Admin.ADMIN + ApiPaths.Clinician.CLINICIAN+ ApiPaths.Clinician.ID)
    public ResponseEntity<ApplicationResponse<Void>> deleteClinician(@PathVariable(name = "id") Integer clinicianId) {
        clinicianService.deleteClinician(clinicianId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                ApplicationResponse.<Void>builder()
                        .success(true)
                        .message("successfully deleted a Clinician")
                        .build()
        );
    }


}
