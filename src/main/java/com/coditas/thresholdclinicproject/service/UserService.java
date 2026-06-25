package com.coditas.thresholdclinicproject.service;

import com.coditas.thresholdclinicproject.constants.ExceptionConstants;
import com.coditas.thresholdclinicproject.dto.users.UserRequest;
import com.coditas.thresholdclinicproject.dto.users.UserResponse;
import com.coditas.thresholdclinicproject.entity.User;
import com.coditas.thresholdclinicproject.enums.Role;
import com.coditas.thresholdclinicproject.exceptions.DuplicateResourceException;
import com.coditas.thresholdclinicproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerAdmin(UserRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException(ExceptionConstants.DUPLICATE_RESOURCE);
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.FRONT_DESK_COORDINATOR);
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        User savedUser = userRepository.save(user);
        return UserResponse.builder()
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }
}
