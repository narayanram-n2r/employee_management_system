package com.rps.capestone.batch11;

import com.rps.capestone.batch11.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.rps.capestone.batch11.repository.RoleRepository;

@SpringBootApplication
public class Batch11Application {

	public static void main(String[] args) {
		SpringApplication.run(Batch11Application.class, args);
	}
	@Bean

	CommandLineRunner init(RoleRepository roleRepository) {

		return args -> {

			Role adminRole = roleRepository.findByRole("ADMIN");
			if (adminRole == null) {
				Role newAdminRole = new Role();
				newAdminRole.setRole("ADMIN");
				roleRepository.save(newAdminRole);
			}
			Role userRole = roleRepository.findByRole("USER");
			if(userRole==null){
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}
		};

	}




	}


