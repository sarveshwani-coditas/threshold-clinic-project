package com.coditas.thresholdclinicproject.dto;

import com.coditas.thresholdclinicproject.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PatientRecordResponse {

    private Integer id;

    private Integer age;

    private Gender gender;

    private String medicalHistory;

    private Instant createdAt;

    private Instant updatedAt;
}
