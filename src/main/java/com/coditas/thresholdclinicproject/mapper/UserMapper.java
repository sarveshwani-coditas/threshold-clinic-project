package com.coditas.thresholdclinicproject.mapper;


import com.coditas.thresholdclinicproject.dto.users.UserRequest;
import com.coditas.thresholdclinicproject.dto.users.UserResponse;
import com.coditas.thresholdclinicproject.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toEntity(UserRequest request);

    UserResponse toDTO(User savedUser);
}
