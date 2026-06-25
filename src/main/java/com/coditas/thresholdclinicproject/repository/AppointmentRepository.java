package com.coditas.thresholdclinicproject.repository;

import com.coditas.thresholdclinicproject.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
