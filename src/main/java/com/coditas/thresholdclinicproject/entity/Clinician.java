package com.coditas.thresholdclinicproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clinician")
public class Clinician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "specialization")
    private String specialization;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
