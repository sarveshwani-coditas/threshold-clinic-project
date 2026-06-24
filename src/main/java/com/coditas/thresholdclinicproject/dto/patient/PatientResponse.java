package com.coditas.thresholdclinicproject.dto.patient;

import com.coditas.utilitymodule.dto.UserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientResponse {

    private Integer id;

    private String name;

    private UserResponse user;
}
