package com.coditas.thresholdclinicproject.dto;

import com.coditas.thresholdclinicproject.dto.users.UserResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClinicianResponse {

    private String name;

    private String specialization;

    private UserResponse user;
}
