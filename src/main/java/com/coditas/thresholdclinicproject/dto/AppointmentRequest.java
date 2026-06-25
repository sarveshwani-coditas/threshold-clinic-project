package com.coditas.thresholdclinicproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentRequest {

    private LocalDateTime time;

    private String reasonForVisit;

}
