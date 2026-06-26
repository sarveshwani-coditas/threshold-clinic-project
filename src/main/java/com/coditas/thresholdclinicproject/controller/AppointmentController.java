package com.coditas.thresholdclinicproject.controller;

import com.coditas.thresholdclinicproject.constants.ApiPaths;
import com.coditas.thresholdclinicproject.dto.*;
import com.coditas.thresholdclinicproject.enums.AppointmentStatus;
import com.coditas.thresholdclinicproject.service.AppointmentService;
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
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PreAuthorize("hasAnyRole('FRONT_DESK_COORDINATOR','PATIENT')")
    @PostMapping(ApiPaths.Appointment.PATIENT + ApiPaths.Appointment.CLINICIAN + ApiPaths.Appointment.BASE)
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

    @PreAuthorize("hasAnyRole('FRONT_DESK_COORDINATOR')")
    @PostMapping(ApiPaths.Admin.ADMIN + ApiPaths.Appointment.BASE + ApiPaths.Appointment.ID)
    public ResponseEntity<ApplicationResponse<AppointmentResponse>> updateDoctor(@PathVariable(name = "id") Integer appointmentId, @Valid @RequestBody ClinicianUpdate request) {

        AppointmentResponse response = appointmentService.updateClinician(appointmentId, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<AppointmentResponse>builder()
                        .success(true)
                        .message("clinician is successfully assigned for this appointment")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasRole('CLINICIAN')")
    @PostMapping(ApiPaths.Appointment.BASE + ApiPaths.Appointment.ID)
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

    @PreAuthorize("hasRole('FRONT_DESK_COORDINATOR')")
    @PutMapping(ApiPaths.Appointment.BASE + ApiPaths.Appointment.ID)
    public ResponseEntity<ApplicationResponse<AppointmentResponse>> updateAppointmentStatus(@PathVariable(name = "id") Integer appointmentId, @Valid @RequestBody StatusRequest request) {

        AppointmentResponse response = appointmentService.updateAppointmentStatus(appointmentId, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<AppointmentResponse>builder()
                        .success(true)
                        .message("the appointment status is updated")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasRole('FRONT_DESK_COORDINATOR')")
    @GetMapping(ApiPaths.Appointment.BASE)
    public ResponseEntity<ApplicationResponse<Page<AppointmentResponse>>> getAppointments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) AppointmentStatus status) {

        Page<AppointmentResponse> response = appointmentService.getAppointments(page, size, status);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<Page<AppointmentResponse>>builder()
                        .success(true)
                        .message("List of appointments successfully retrieved")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('FRONT_DESK_COORDINATOR', 'CLINICIAN')")
    @GetMapping(ApiPaths.Clinician.CLINICIAN + ApiPaths.Clinician.ID + ApiPaths.Appointment.BASE)
    public ResponseEntity<ApplicationResponse<Page<AppointmentResponse>>> getAppointmentsByClinicianId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @PathVariable(name = "id") Integer clinicianId
    ) {

        Page<AppointmentResponse> response = appointmentService.getAppointmentsByClinicianId(page, size, clinicianId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<Page<AppointmentResponse>>builder()
                        .success(true)
                        .message("List of appointments successfully retrieved")
                        .data(response)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('FRONT_DESK_COORDINATOR', 'PATIENT')")
    @GetMapping(ApiPaths.Patient.BASE + ApiPaths.Patient.ID + ApiPaths.Appointment.BASE)
    public ResponseEntity<ApplicationResponse<Page<AppointmentResponse>>> getAppointmentsByPatientId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @PathVariable Integer patientId
    ) {

        Page<AppointmentResponse> response = appointmentService.getAppointmentsByPatientId(page, size, patientId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApplicationResponse.<Page<AppointmentResponse>>builder()
                        .success(true)
                        .message("List of appointments successfully retrieved")
                        .data(response)
                        .build()
        );
    }


}
