package com.coditas.thresholdclinicproject.constants;

public final class ApiPaths {

    private ApiPaths() {
    }

    public static final String BASE_PATH = "/api/v1";
    public static final String LOGIN = "/login";

    public static class Patient {
        private Patient() {
        }

        public static final String BASE = BASE_PATH + "/patients";
        public static final String ID = "/{id}";
    }

    public static class Clinician {
        private Clinician() {
        }

        public static final String BASE = BASE_PATH + "/clinician";
        public static final String ID = "/{id}";
    }

    public static class Admin {
        private Admin() {
        }

        public static final String BASE = BASE_PATH + "/admin";
        public static final String ID = "/{id}";
    }
}
