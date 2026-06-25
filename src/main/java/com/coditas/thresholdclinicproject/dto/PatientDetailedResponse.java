package com.coditas.thresholdclinicproject.dto;

import com.coditas.thresholdclinicproject.dto.users.UserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDetailedResponse {
    private Integer id;

    private String name;

    private UserResponse user;

    private PatientRecordResponse patientRecord;
}
