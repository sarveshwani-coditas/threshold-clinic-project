package com.coditas.thresholdclinicproject.dto;

import com.coditas.thresholdclinicproject.entity.Clinician;
import com.coditas.thresholdclinicproject.entity.Patient;
import com.coditas.thresholdclinicproject.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AppointmentResponse {

    private Patient patient;

    private Clinician clinician;

    private Instant time;

    private AppointmentStatus status;

    private String clinicalNotes;

    private String reasonForVisit;
}
