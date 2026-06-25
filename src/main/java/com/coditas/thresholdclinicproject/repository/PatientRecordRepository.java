package com.coditas.thresholdclinicproject.repository;

import com.coditas.thresholdclinicproject.entity.PatientRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, Integer> {
}
