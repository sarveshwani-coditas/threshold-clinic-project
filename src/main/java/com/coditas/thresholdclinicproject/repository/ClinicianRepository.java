package com.coditas.thresholdclinicproject.repository;

import com.coditas.thresholdclinicproject.entity.Clinician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicianRepository extends JpaRepository<Clinician, Long> {
}
