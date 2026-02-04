package com.example.MiniPetProject.user.service;

import com.example.MiniPetProject.user.api.UserMapper;
import com.example.MiniPetProject.user.api.UserRegistrationDto;
import com.example.MiniPetProject.user.api.UserResponseDto;
import com.example.MiniPetProject.user.domain.User;
import com.example.MiniPetProject.user.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class    UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDto> findAll(){
        List<UserResponseDto> foundUsers;
        foundUsers = userRepository.findAll().stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toCollection(ArrayList::new));
        return foundUsers;
    }


    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return userMapper.toResponseDto(user);
    }

    public User findUserById1(Long id) {
        return  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }


    @Transactional
    public UserRegistrationDto createUser(UserRegistrationDto userRegistrationDto) {
        User user = userMapper.toEntity(userRegistrationDto);
        user = userRepository.save(user);
        return userMapper.toRegistrationDto(user);
    }
}
