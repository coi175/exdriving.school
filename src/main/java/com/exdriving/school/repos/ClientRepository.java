package com.exdriving.school.repos;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Notification;
import com.exdriving.school.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    // получаем запись по логину
    <Optional> Client findClientById(Integer id);
}
