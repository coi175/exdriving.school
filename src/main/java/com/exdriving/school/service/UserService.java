package com.exdriving.school.service;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;

public interface UserService {
    public void addClient(String login, String password, Client client);
    public void addInstructor(String login, String password, Instructor instructor);
}
