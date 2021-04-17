package com.exdriving.school.repos;

import com.exdriving.school.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с уведомлениями
 */
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
