package com.example.MiniPetProject.user.repository;

import com.example.MiniPetProject.user.api.UserResponseDto;
import com.example.MiniPetProject.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Long id);
}
