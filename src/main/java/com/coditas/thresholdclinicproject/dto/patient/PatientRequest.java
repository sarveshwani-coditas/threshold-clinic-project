package com.coditas.thresholdclinicproject.dto.patient;

import com.coditas.thresholdclinicproject.dto.users.UserRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequest {

    @NotBlank
    private String name;

    private UserRequest user;
}
