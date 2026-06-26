package com.coditas.thresholdclinicproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ThresholdClinicProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThresholdClinicProjectApplication.class, args);
    }

}
