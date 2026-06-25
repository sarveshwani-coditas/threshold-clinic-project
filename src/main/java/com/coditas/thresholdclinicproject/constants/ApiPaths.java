package com.coditas.thresholdclinicproject.constants;

public final class ApiPaths {

    private ApiPaths() {
    }

    public static final String BASE_PATH = "/api/v1";
    public static final String LOGIN = "/login";

    public static class Patient {
        private Patient() {
        }

        public static final String BASE = "/patients";
        public static final String ID = "/{patientId}";

        public static final String PATIENTRECORDS = "/patient-record";
    }

    public static class Clinician {
        private Clinician() {
        }

        public static final String BASE = BASE_PATH + "/clinician";
        public static final String CLINICIAN = "/clinician";
        public static final String ID = "/{id}";
    }

    public static class Appointment {
        private Appointment() {
        }

        public static final String BASE = "/appointments";
        public static final String PATIENT = "/patient/{patientId}";
        public  static final String CLINICIAN = "/clinician/{clinicianId}";
        public static final String ID = "/{id}";
    }

    public static class Admin {
        private Admin() {
        }

        public static final String BASE = BASE_PATH + "/admin";
        public static final String ADMIN ="/admin";
        public static final String ID = "/{id}";
    }
}
