package com.coditas.thresholdclinicproject.controller;

import com.coditas.thresholdclinicproject.dto.ApplicationResponse;
import com.coditas.thresholdclinicproject.dto.AppointmentRequest;
import com.coditas.thresholdclinicproject.entity.Appointment;
import com.coditas.thresholdclinicproject.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<ApplicationResponse<AppointmentResponse>> bookAppointment(@Valid @RequestBody AppointmentRequest request){

    }


}
