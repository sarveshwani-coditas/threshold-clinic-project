package com.coditas.thresholdclinicproject.dto.patient;

import com.coditas.utilitymodule.dto.UserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequest {

    private String name;

    private UserRequest user;
}
