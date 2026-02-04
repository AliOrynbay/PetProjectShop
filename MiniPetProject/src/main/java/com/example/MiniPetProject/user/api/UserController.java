package com.example.MiniPetProject.user.api;

import com.example.MiniPetProject.user.domain.User;
import com.example.MiniPetProject.user.repository.UserRepository;
import com.example.MiniPetProject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id) , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserRegistrationDto> createUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        return new ResponseEntity<>(userService.createUser(userRegistrationDto), HttpStatus.CREATED);
    }
}
