package com.coditas.thresholdclinicproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private Object errors;
}
