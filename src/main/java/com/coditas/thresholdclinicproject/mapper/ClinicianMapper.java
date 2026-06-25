package com.coditas.thresholdclinicproject.mapper;

import com.coditas.thresholdclinicproject.dto.ClinicianRequest;
import com.coditas.thresholdclinicproject.dto.ClinicianResponse;
import com.coditas.thresholdclinicproject.entity.Clinician;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ClinicianMapper {

    Clinician toEntity(ClinicianRequest request);

    ClinicianResponse toDTO(Clinician savedClinician);
}
