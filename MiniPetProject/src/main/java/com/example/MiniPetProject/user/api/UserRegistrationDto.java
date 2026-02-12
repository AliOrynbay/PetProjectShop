package com.example.MiniPetProject.user.api;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
}
