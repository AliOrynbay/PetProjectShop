package com.example.MiniPetProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MiniPetProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniPetProjectApplication.class, args);
	}

}
