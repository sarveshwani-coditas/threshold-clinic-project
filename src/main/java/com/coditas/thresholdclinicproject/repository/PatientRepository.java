package com.coditas.thresholdclinicproject.repository;

import com.coditas.thresholdclinicproject.entity.Patient;
import com.coditas.thresholdclinicproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findByUser(User user);
}
