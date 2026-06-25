package com.coditas.thresholdclinicproject.mapper;

import com.coditas.thresholdclinicproject.dto.patient.PatientResponse;
import com.coditas.thresholdclinicproject.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PatientMapper {

    PatientResponse toDTO(Patient savedPatient);
}
