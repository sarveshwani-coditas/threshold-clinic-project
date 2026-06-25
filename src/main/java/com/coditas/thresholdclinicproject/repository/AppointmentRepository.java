package com.coditas.thresholdclinicproject.repository;

import com.coditas.thresholdclinicproject.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    boolean existByTime(LocalDateTime time);
}
