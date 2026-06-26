package com.coditas.thresholdclinicproject.service;

import com.coditas.thresholdclinicproject.constants.ExceptionConstants;
import com.coditas.thresholdclinicproject.dto.*;
import com.coditas.thresholdclinicproject.entity.Appointment;
import com.coditas.thresholdclinicproject.entity.Clinician;
import com.coditas.thresholdclinicproject.entity.Patient;
import com.coditas.thresholdclinicproject.enums.AppointmentStatus;
import com.coditas.thresholdclinicproject.exceptions.ClinicalNoteException;
import com.coditas.thresholdclinicproject.exceptions.ResourceNotFoundException;
import com.coditas.thresholdclinicproject.mapper.AppointmentMapper;
import com.coditas.thresholdclinicproject.repository.AppointmentRepository;
import com.coditas.thresholdclinicproject.repository.ClinicianRepository;
import com.coditas.thresholdclinicproject.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ClinicianRepository clinicianRepository;
    private final AppointmentMapper appointmentMapper;
    private final MailService mailService;

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
        mailService.sendEmail(
                patient.getUser().getEmail(),
                "Appointment Confirmed",
                "Hi "+ patient.getName()+" Your appointment has been booked and don't forget to complete your intake form before consultation");

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
        mailService.sendEmail(
                appointment.getPatient().getUser().getEmail(),
                "Clinical Notes Available",
                "Your last Visit's Clinical Notes are Available by Dr. "+appointment.getClinician().getName()
        );

        log.info("Successfully added an clinical for appointment with id {} ", bookedAppointment.getId());
        return appointmentMapper.toDTO(bookedAppointment);
    }

    public AppointmentResponse updateAppointmentStatus(Integer appointmentId, @Valid StatusRequest request) {

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.APPOINTMENT_NOT_EXIST)
        );
        appointment.setStatus(request.getStatus());
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        log.info("Successfully updated appointment status to {} ", request.getStatus());
        return appointmentMapper.toDTO(updatedAppointment);
    }

    public Page<AppointmentResponse> getAppointments(int page, int size, AppointmentStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        log.info("Successfully retrieved appointments");
        return appointmentRepository
                .findAllByStatus(status, pageable)
                .map(appointmentMapper::toDTO);
    }

    public Page<AppointmentResponse> getAppointmentsByClinicianId(int page, int size, Integer clinicianId) {
        Pageable pageable = PageRequest.of(page, size);
        Clinician clinician = clinicianRepository.findById(clinicianId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.CLINICIAN_NOT_FOUND)
        );

        log.info("Successfully retrieved appointments");
        return appointmentRepository
                .findAllByClinician(clinician, pageable)
                .map(appointmentMapper::toDTO);
    }

    public Page<AppointmentResponse> getAppointmentsByPatientId(int page, int size, Integer patientId) {
        Pageable pageable = PageRequest.of(page, size);

        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.CLINICIAN_NOT_FOUND)
        );

        log.info("Successfully retrieved appointments");
        return appointmentRepository
                .findAllByPatient(patient, pageable)
                .map(appointmentMapper::toDTO);
    }

    public AppointmentResponse updateClinician(Integer appointmentId, @Valid ClinicianUpdate request) {

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.APPOINTMENT_NOT_EXIST)
        );

        Clinician clinician = clinicianRepository.findById(request.getClinicianId()).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionConstants.CLINICIAN_NOT_FOUND)
        );
        log.info("Successfully updated clinician for appointment with id {} " + appointmentId);
        appointment.setClinician(clinician);
        return appointmentMapper.toDTO(appointment);

    }
}
