package com.coditas.thresholdclinicproject.constants;

public final class ExceptionConstants {

    private ExceptionConstants() {
    }

    public static final String USER_NOT_FOUND = "USER_NOT_FOUND_FOR_PROVIDED_ID";
    public static final String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND FOR PROVIDED ID";
    public static final String INVALID_CREDENTIAL = "INVALID_CREDENTIALS_SENT";
    public static final String UNAUTHENTICATED_USER = "USER_IS_NOT_AUTHENTICATED";
    public static final String DUPLICATE_RESOURCE = "THE_RESOURCE_ALREADY_EXIST";
    public static final String ROLE_MISMATCH = "NOT_A_VALID_ROLE_FOR_THIS_OPERATION";
    public static final String USER_ROLE_MISMATCH = "THE_RETRIEVED_USER_DOES_NOT_HAVE_REQUIRED_ROLE";
    public static final String REFRESHTOKEN_EXPIRED = "REFRESH_TOKEN_EXPIRED_LOGIN_AGAIN";
    public static final String PATIENT_NOT_FOUND = "patient not exist for this id";
    public static final String CLINICIAN_NOT_FOUND = "clinician not exist for this id";
    public static final String TIME_SLOT_IS_OCCUPIED = "The chosen time slot is already booked, please choose another one";
    public static final String APPOINTMENT_NOT_EXIST = "The appointment does not exist with this id";



}