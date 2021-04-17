package com.exdriving.school.service;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;

import java.util.List;

/**
 * Сервис, через который контроллер работает с клиентами в базе данных
 */
public interface ClientService {
    /**
     * Возвращает клиента из базы данных по ID
     * @param id
     * @return Client
     */
    public Client findClientByID(Integer id);

    /**
     * Возвращает всех клиентов из базы данных
     * @return List<Client>
     */
    public List<Client> getAllClients();

    /**
     * Удаляет все уведомления у клиента (удаляет записи из связной таблицы notification_list @ManyToMany),
     * но не удаляет сами уведомления
     * @param client
     */
    public void removeNotifications(Client client);

    /**
     * Отменяет запись на занятие. Обнуляет поле lesson_id у Клиента, изменяет кол-во оставшихся часов у клиента
     * @param client
     */
    public void cancelRecording(Client client);

    /**
     * Запись на занятие клиента по ID. Добавляет занятие в поле lesson_id у Клиента, изменяет кол-во оставшихся часов
     * @param client
     * @param id
     */
    public void recordToLesson(Client client, Integer id);

    /**
     * Изменяет информацию о клиенте (прикрепленный инструктор, оставшиеся часы)
     * @param client
     * @param instructor
     * @param remainingHours
     */
    public void changeData(Client client, Instructor instructor, Integer remainingHours);

    /**
     * Создает нового клиента (запись в базе данных)
     * @param firstName
     * @param lastName
     * @param email
     * @param hoursLimit
     * @param instructor
     * @return Client
     */
    public Client createClient(String firstName, String lastName, String email, Integer hoursLimit, Instructor instructor);
}
