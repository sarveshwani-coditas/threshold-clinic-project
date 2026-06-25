package com.coditas.thresholdclinicproject.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserResponse {

    private String username;

    private String email;

    private Instant createdAt;
}
