package com.coditas.thresholdclinicproject.repository;

import com.coditas.thresholdclinicproject.entity.Appointment;
import com.coditas.thresholdclinicproject.entity.Clinician;
import com.coditas.thresholdclinicproject.entity.Patient;
import com.coditas.thresholdclinicproject.enums.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Page<Appointment> findAllByStatus(AppointmentStatus status, Pageable pageable);


    Page<Appointment> findAllByPatient(Patient patient, Pageable pageable);

    Page<Appointment> findAllByClinician(Clinician clinician, Pageable pageable);

    List<Appointment> findAllByTime(LocalDate afterTwoDays);
}
