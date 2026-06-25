package com.coditas.thresholdclinicproject.controller;

import com.coditas.thresholdclinicproject.constants.ApiPaths;
import com.coditas.thresholdclinicproject.dto.ApplicationResponse;
import com.coditas.thresholdclinicproject.dto.users.UserRequest;
import com.coditas.thresholdclinicproject.dto.users.UserResponse;
import com.coditas.thresholdclinicproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.Admin.BASE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApplicationResponse<UserResponse>> registerAdmin(@RequestBody UserRequest request){
        UserResponse response = userService.registerAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApplicationResponse.<UserResponse>builder()
                        .success(true)
                        .message("You have been successfully registered")
                        .data(response)
                        .build()
        );
    }

}
