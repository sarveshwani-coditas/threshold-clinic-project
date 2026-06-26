package com.coditas.thresholdclinicproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentRequest {

    private LocalDateTime time;

    @NotBlank
    private String reasonForVisit;

}
