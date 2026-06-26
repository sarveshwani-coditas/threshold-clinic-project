package com.coditas.thresholdclinicproject.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String username;

    @Email
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{4,}$",
            message = "Password must be at least 4 characters and contain one uppercase letter, one digit and one special character"
    )
    private String password;

}
