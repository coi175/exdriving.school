package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Lesson;
import com.exdriving.school.domain.Notification;
import com.exdriving.school.repos.NotificationRepository;
import com.exdriving.school.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Set;

@Service
public class NotificationServiceIml implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public void createNotification(Set<Client> clients, Lesson lesson) {
        Notification notification = new Notification();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String date = format.format(lesson.getDate());
        notification.setName("Занятие " + date + " отменено!");
        notification.setText("Инструктор отменил занятие. Пожалуйста, запишитесь на другое занятие. Часы за отмененное занятие востановлены.");
        for(Client client : clients) {
            client.addNotification(notification);
        }
        notificationRepository.saveAndFlush(notification);
    }
}
