package com.example.MiniPetProject.user.api;

import com.example.MiniPetProject.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toResponseDto(User user);
    UserRegistrationDto toRegistrationDto(User user);
    User toEntity(UserResponseDto userResponseDto);

    @Mapping(source ="passwordHash" , target = "passwordHash")
    User toEntity(UserRegistrationDto userRegistrationDto);
}
