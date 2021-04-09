package com.exdriving.school.service;

import com.exdriving.school.domain.Client;

public interface ClientService {
    // поиск клиента по ID
    public Client findClientByID(Integer id);
    // удалить все уведомления у клиента (удаляет связи из auth_data, сами уведомления лежат в базе данных)
    public void removeNotifications(Client client);
    // отменить запись (удаляеть связь с занятием у клиента, меняет кол-во часов у клиента)
    public void cancelRecording(Client client);
    // запись на занятие
    public void recordToLesson(Client client, Integer id);
}
