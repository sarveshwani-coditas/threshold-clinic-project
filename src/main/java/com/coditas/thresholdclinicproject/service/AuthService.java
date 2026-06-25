package com.coditas.thresholdclinicproject.service;

import com.coditas.thresholdclinicproject.constants.ExceptionConstants;
import com.coditas.thresholdclinicproject.dto.login.LoginRequest;
import com.coditas.thresholdclinicproject.dto.login.LoginResponse;
import com.coditas.thresholdclinicproject.exceptions.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            throw new InvalidCredentialsException(ExceptionConstants.INVALID_CREDENTIAL);
        }


        if (authentication.isAuthenticated()) {
            UserDetails user = (UserDetails) authentication.getPrincipal();

            assert user != null;
            String accessToken = jwtService.generateAccessToken(user);
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .build();
        } else {
            throw new InvalidCredentialsException(ExceptionConstants.INVALID_CREDENTIAL);
        }
    }
}
