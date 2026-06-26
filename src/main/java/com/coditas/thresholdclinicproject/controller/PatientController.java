package com.coditas.thresholdclinicproject.controller;

import com.coditas.thresholdclinicproject.constants.ApiPaths;
import com.coditas.thresholdclinicproject.dto.ApplicationResponse;
import com.coditas.thresholdclinicproject.dto.PatientDetailedResponse;
import com.coditas.thresholdclinicproject.dto.PatientRecordRequest;
import com.coditas.thresholdclinicproject.dto.patient.PatientRequest;
import com.coditas.thresholdclinicproject.dto.patient.PatientResponse;
import com.coditas.thresholdclinicproject.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.BASE_PATH)
public class PatientController {

    private final PatientService patientService;

    @PostMapping(ApiPaths.Patient.BASE)
    public ResponseEntity<ApplicationResponse<PatientResponse>> registerPatient(@RequestBody PatientRequest request) {
        PatientResponse response = patientService.registerPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApplicationResponse.<PatientResponse>builder()
                        .success(true)
                        .message("You have been successfully registered")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping(ApiPaths.Patient.BASE + ApiPaths.Patient.ID + ApiPaths.Patient.PATIENTRECORDS)
    public ResponseEntity<ApplicationResponse<PatientDetailedResponse>> fillPatientRecord(@PathVariable Integer patientId, @RequestBody PatientRecordRequest request) {
        PatientDetailedResponse response = patientService.fillPatientRecord(patientId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApplicationResponse.<PatientDetailedResponse>builder()
                        .success(true)
                        .message("successfully updated patient records")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasRole('CLINICIAN')")
    @GetMapping(ApiPaths.Clinician.CLINICIAN + ApiPaths.Patient.BASE + ApiPaths.Patient.ID)
    public ResponseEntity<ApplicationResponse<PatientDetailedResponse>> getPatientInfoByClinician(@PathVariable Integer patientId) {
        PatientDetailedResponse response = patientService.getPatientInfo(patientId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<PatientDetailedResponse>builder()
                        .success(true)
                        .message("successfully retrieved patient info")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(ApiPaths.Patient.BASE)
    public ResponseEntity<ApplicationResponse<PatientDetailedResponse>> getPatientInfoAsPatient() {
        PatientDetailedResponse response = patientService.getPatientInfoAsPatient();

        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<PatientDetailedResponse>builder()
                        .success(true)
                        .message("successfully retrieved patient info")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasRole('FRONT_DESK_COORDINATOR')")
    @GetMapping(ApiPaths.Admin.BASE + ApiPaths.Patient.BASE + ApiPaths.Patient.ID)
    public ResponseEntity<ApplicationResponse<PatientResponse>> getPatientInfoByAdmin(@PathVariable Integer patientId) {
        PatientResponse response = patientService.getPatientInfoByAdmin(patientId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<PatientResponse>builder()
                        .success(true)
                        .message("successfully retrieved patient info")
                        .data(response)
                        .build()
        );
    }
}
