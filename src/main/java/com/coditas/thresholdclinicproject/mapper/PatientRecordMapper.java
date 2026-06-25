package com.coditas.thresholdclinicproject.mapper;

import com.coditas.thresholdclinicproject.dto.PatientRecordRequest;
import com.coditas.thresholdclinicproject.entity.PatientRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientRecordMapper {


    PatientRecord toEntity(PatientRecordRequest request);
}
