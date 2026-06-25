package com.coditas.thresholdclinicproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AppointmentRequest {

    private Instant time;

    private String reasonForVisit;

}
