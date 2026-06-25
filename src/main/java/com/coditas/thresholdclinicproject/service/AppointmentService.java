package com.coditas.thresholdclinicproject.service;

import com.coditas.thresholdclinicproject.constants.ExceptionConstants;
import com.coditas.thresholdclinicproject.dto.AppointmentRequest;
import com.coditas.thresholdclinicproject.dto.AppointmentResponse;
import com.coditas.thresholdclinicproject.entity.Appointment;
import com.coditas.thresholdclinicproject.entity.Clinician;
import com.coditas.thresholdclinicproject.entity.Patient;
import com.coditas.thresholdclinicproject.enums.AppointmentStatus;
import com.coditas.thresholdclinicproject.exceptions.ResourceNotFoundException;
import com.coditas.thresholdclinicproject.mapper.AppointmentMapper;
import com.coditas.thresholdclinicproject.repository.AppointmentRepository;
import com.coditas.thresholdclinicproject.repository.ClinicianRepository;
import com.coditas.thresholdclinicproject.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ClinicianRepository clinicianRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentResponse bookAppointment(Integer patientId, Integer clinicianId, @Valid AppointmentRequest request) {

        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.RESOURCE_NOT_FOUND)
        );

        Clinician clinician = clinicianRepository.findById(clinicianId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.RESOURCE_NOT_FOUND)
        );

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setClinician(clinician);
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setTime(request.getTime());
        appointment.setReasonForVisit(request.getReasonForVisit());

        Appointment bookedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toDTO(bookedAppointment);
    }
}
