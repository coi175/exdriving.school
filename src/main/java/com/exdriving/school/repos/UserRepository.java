package com.exdriving.school.repos;

import com.exdriving.school.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с таблице auth_data (данные для входа)
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    // получаем запись по логину
    <Optional> User findByUsername(String username);
}
