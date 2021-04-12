package com.exdriving.school;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;
import com.exdriving.school.domain.User;
import com.exdriving.school.domain.security.UserDetailsServiceIml;
import com.exdriving.school.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@SpringBootApplication
public class SchoolApplication {
	private static final Logger log = LoggerFactory.getLogger(SchoolApplication.class);

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}
