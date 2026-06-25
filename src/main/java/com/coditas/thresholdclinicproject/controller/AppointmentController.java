package com.coditas.thresholdclinicproject.controller;

import com.coditas.thresholdclinicproject.constants.ApiPaths;
import com.coditas.thresholdclinicproject.dto.ApplicationResponse;
import com.coditas.thresholdclinicproject.dto.AppointmentRequest;
import com.coditas.thresholdclinicproject.dto.AppointmentResponse;
import com.coditas.thresholdclinicproject.dto.ClinicalNote;
import com.coditas.thresholdclinicproject.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PreAuthorize("hasAnyRole('FRONT_DESK_COORDINATOR','PATIENT')")
    @PostMapping(ApiPaths.BASE_PATH + ApiPaths.Appointment.PATIENT + ApiPaths.Appointment.CLINICIAN + ApiPaths.Appointment.BASE)
    public ResponseEntity<ApplicationResponse<AppointmentResponse>> bookAppointment(@PathVariable Integer patientId, @PathVariable Integer clinicianId, @Valid @RequestBody AppointmentRequest request) {

        AppointmentResponse response = appointmentService.bookAppointment(patientId, clinicianId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApplicationResponse.<AppointmentResponse>builder()
                .success(true)
                .message("your appointment is successfully booked")
                .data(response)
                .build()
        );
    }

    @PreAuthorize("hasRole('CLINICIAN')")
    @PostMapping(ApiPaths.BASE_PATH + ApiPaths.Appointment.BASE+ApiPaths.Appointment.ID)
    public ResponseEntity<ApplicationResponse<AppointmentResponse>> addClinicalNote(@PathVariable(name = "id") Integer appointmentId,
                                                                                    @Valid @RequestBody ClinicalNote request) {

        AppointmentResponse response = appointmentService.addClinicalNote(appointmentId, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<AppointmentResponse>builder()
                        .success(true)
                        .message("Successfully added clinical notes")
                        .data(response)
                        .build()
        );
    }


}
