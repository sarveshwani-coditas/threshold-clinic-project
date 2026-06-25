package com.coditas.thresholdclinicproject.service;

import com.coditas.thresholdclinicproject.constants.ExceptionConstants;
import com.coditas.thresholdclinicproject.dto.AppointmentRequest;
import com.coditas.thresholdclinicproject.dto.AppointmentResponse;
import com.coditas.thresholdclinicproject.dto.ClinicalNote;
import com.coditas.thresholdclinicproject.entity.Appointment;
import com.coditas.thresholdclinicproject.entity.Clinician;
import com.coditas.thresholdclinicproject.entity.Patient;
import com.coditas.thresholdclinicproject.enums.AppointmentStatus;
import com.coditas.thresholdclinicproject.exceptions.ClinicalNoteException;
import com.coditas.thresholdclinicproject.exceptions.DuplicateResourceException;
import com.coditas.thresholdclinicproject.exceptions.ResourceNotFoundException;
import com.coditas.thresholdclinicproject.mapper.AppointmentMapper;
import com.coditas.thresholdclinicproject.repository.AppointmentRepository;
import com.coditas.thresholdclinicproject.repository.ClinicianRepository;
import com.coditas.thresholdclinicproject.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ClinicianRepository clinicianRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentResponse bookAppointment(Integer patientId, Integer clinicianId, @Valid AppointmentRequest request) {

        if (appointmentRepository.existByTime(request.getTime())) {
            throw new DuplicateResourceException(ExceptionConstants.TIME_SLOT_IS_OCCUPIED);
        }

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

        log.info("Successfully booked an appointment with id {} ", bookedAppointment.getId());
        return appointmentMapper.toDTO(bookedAppointment);
    }

    public AppointmentResponse addClinicalNote(Integer appointmentId, @Valid ClinicalNote request) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.RESOURCE_NOT_FOUND)
        );
        if (appointment.getClinicalNotes() != null) {
            throw new ClinicalNoteException("The clinical notes already exist!");
        }
        appointment.setClinicalNotes(request.getClinicalNote());
        Appointment bookedAppointment = appointmentRepository.save(appointment);

        log.info("Successfully added an clinical for appointment with id {} ", bookedAppointment.getId());
        return appointmentMapper.toDTO(bookedAppointment);
    }
}
