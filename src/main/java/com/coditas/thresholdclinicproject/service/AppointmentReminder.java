package com.coditas.thresholdclinicproject.service;

import com.coditas.thresholdclinicproject.entity.Appointment;
import com.coditas.thresholdclinicproject.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentReminder {

    private final AppointmentRepository appointmentRepository;

    private final MailService mailService;

    @Scheduled(cron = "0 0 9 * * *")
    public void sendAppointmentReminders(){

        LocalDateTime afterTwoDays = LocalDateTime.now().plusDays(2);

        List<Appointment> appointmentList = appointmentRepository.findAllByTime(afterTwoDays);

        for(Appointment appointment : appointmentList){
            String patientEmail = appointment.getPatient().getUser().getEmail();
            String patientName = appointment.getPatient().getName();

            mailService.sendEmail(
                    patientEmail,
                    "details reminder",
                    """
                            Hi %s,
                            
                            You have an upcoming appointment in next 2 days so please fill out the required details before visiting
                            
                            Thank you.
                            """.formatted(patientName)
            );
        }


    }
}
