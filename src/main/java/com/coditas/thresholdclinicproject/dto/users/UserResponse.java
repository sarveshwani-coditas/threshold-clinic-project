package com.coditas.thresholdclinicproject.dto.users;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserResponse {

    private String username;

    private String email;

    private Instant createdAt;
}
