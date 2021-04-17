package com.exdriving.school.service;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Lesson;

import java.util.Set;

/**
 * Сервис, через который контроллер работает с уведомлениями в базе данных
 */
public interface NotificationService {
    /**
     * Создает новое уведомление в базе данных
     * @param clients
     * @param lesson
     */
    public void createNotification(Set<Client> clients, Lesson lesson);
}
