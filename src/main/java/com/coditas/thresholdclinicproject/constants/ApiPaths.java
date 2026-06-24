package com.coditas.thresholdclinicproject.constants;

public final class ApiPaths {

    private ApiPaths() {
    }

    public static final String BASE_PATH = "/api/v1";

    public static class Patient {
        private Patient() {
        }

        public static final String BASE = BASE_PATH + "/patients";
        public static final String ID = "/{id}";
    }
}
