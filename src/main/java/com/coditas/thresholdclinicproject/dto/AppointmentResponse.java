package com.coditas.thresholdclinicproject.dto;

import com.coditas.thresholdclinicproject.dto.patient.PatientResponse;
import com.coditas.thresholdclinicproject.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentResponse {

    private PatientResponse patient;

    private ClinicianResponse clinician;

    private LocalDateTime time;

    private AppointmentStatus status;

    private String clinicalNotes;

    private String reasonForVisit;
}
