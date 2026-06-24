package com.coditas.thresholdclinicproject.entity;

import com.coditas.thresholdclinicproject.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "clinician_id")
    private Clinician clinician;

    @Column(name = "time")
    private Instant time;

    @Column(name = "status")
    private AppointmentStatus status;

    @Column(name = "clinical_notes")
    private String clinicalNotes;

    @Column(name = "reason_for_visit")
    private String reasonForVisit;

}
