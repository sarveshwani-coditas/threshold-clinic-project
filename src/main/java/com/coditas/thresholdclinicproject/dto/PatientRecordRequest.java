package com.coditas.thresholdclinicproject.dto;

import com.coditas.thresholdclinicproject.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRecordRequest {

    private Integer age;

    private Gender gender;

    private String medicalHistory;

}
