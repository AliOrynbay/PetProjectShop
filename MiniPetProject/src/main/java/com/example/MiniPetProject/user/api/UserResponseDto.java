package com.example.MiniPetProject.user.api;


import com.example.MiniPetProject.user.domain.UserStatus;
import lombok.*;

import java.io.Serializable;
import java.util.StringTokenizer;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private UserStatus status;
}
