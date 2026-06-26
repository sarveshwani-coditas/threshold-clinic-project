package com.coditas.thresholdclinicproject.controller;

import com.coditas.thresholdclinicproject.constants.ApiPaths;
import com.coditas.thresholdclinicproject.dto.ApplicationResponse;
import com.coditas.thresholdclinicproject.dto.login.LoginRequest;
import com.coditas.thresholdclinicproject.dto.login.LoginResponse;
import com.coditas.thresholdclinicproject.service.AuthService;
import com.coditas.thresholdclinicproject.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.BASE_PATH)
public class AuthController {

    private final AuthService authService;
    private final MailService mailService;

    @PostMapping(ApiPaths.LOGIN)
    public ResponseEntity<ApplicationResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);

        return ResponseEntity.ok().body(
                ApplicationResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Successfully logged In")
                        .data(response)
                        .build()
        );

    }

    @GetMapping("/email")
    public String send() {

        mailService.sendEmail(
                "vaibhav.patil@coditas.com",
                "Spring Boot Test",
                "Email is working"
        );

        return "Sent";
    }




}
