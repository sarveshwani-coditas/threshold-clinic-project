package com.coditas.thresholdclinicproject.mapper;

import com.coditas.thresholdclinicproject.dto.AppointmentResponse;
import com.coditas.thresholdclinicproject.entity.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PatientMapper.class, ClinicianMapper.class})
public interface AppointmentMapper {
    AppointmentResponse toDTO(Appointment bookedAppointment);
}
