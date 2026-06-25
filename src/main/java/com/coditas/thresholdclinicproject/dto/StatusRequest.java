package com.coditas.thresholdclinicproject.dto;

import com.coditas.thresholdclinicproject.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusRequest {

    private AppointmentStatus status;
}
