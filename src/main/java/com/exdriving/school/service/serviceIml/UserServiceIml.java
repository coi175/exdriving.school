package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;
import com.exdriving.school.domain.User;
import com.exdriving.school.repos.UserRepository;
import com.exdriving.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIml implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void addClient(String login, String password, Client client) {
        User user = new User();
        user.setUsername(login);
        user.setRole("CLIENT");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoded = bCryptPasswordEncoder.encode(password);
        user.setPassword(encoded);
        user.setClientID(client.getId());
        userRepository.saveAndFlush(user);
    }

    @Override
    public void addInstructor(String login, String password, Instructor instructor) {
        User user = new User();
        user.setUsername(login);
        user.setRole("INSTRUCTOR");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoded = bCryptPasswordEncoder.encode(password);
        user.setPassword(encoded);
        user.setInstructorID(instructor.getId());
        userRepository.saveAndFlush(user);
    }
}
