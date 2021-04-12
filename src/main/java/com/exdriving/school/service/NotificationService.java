package com.exdriving.school.service;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Lesson;

import java.util.Set;

public interface NotificationService {
    public void createNotification(Set<Client> clients, Lesson lesson);
}
