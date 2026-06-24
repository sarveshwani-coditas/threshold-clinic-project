package com.coditas.thresholdclinicproject.dto.patient;

import com.coditas.thresholdclinicproject.dto.users.UserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequest {

    private String name;

    private UserRequest user;
}
