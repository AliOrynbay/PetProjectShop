package com.example.MiniPetProject.user.service;

import com.example.MiniPetProject.user.api.UserMapper;
import com.example.MiniPetProject.user.api.UserRegistrationDto;
import com.example.MiniPetProject.user.api.UserResponseDto;
import com.example.MiniPetProject.user.domain.User;
import com.example.MiniPetProject.user.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class    UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDto> findAll(){
        log.info("Trying to find all users");
        List<UserResponseDto> foundUsers;
        foundUsers = userRepository.findAll().stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toCollection(ArrayList::new));
        log.info("Found {} users", foundUsers.size());
        return foundUsers;
    }


    public UserResponseDto findUserById(Long id) {
        log.info("Trying to find user by id {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        log.info("Found user {}", user);
        return userMapper.toResponseDto(user);
    }

    public User findUserById1(Long id) {
        log.info("Trying to find user by id {}", id);
        return  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }


    @Transactional
    public UserRegistrationDto createUser(UserRegistrationDto userRegistrationDto) {
        log.info("Trying to create user {}", userRegistrationDto);
        User user = userMapper.toEntity(userRegistrationDto);
        user = userRepository.save(user);
        log.info("Created user {}", user);
        return userMapper.toRegistrationDto(user);
    }
}
