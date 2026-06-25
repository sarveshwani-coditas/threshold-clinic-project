package com.coditas.thresholdclinicproject.dto;

import com.coditas.thresholdclinicproject.dto.users.UserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicianRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String specialization;

    @NotNull
    private UserRequest user;
}
