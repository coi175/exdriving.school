package com.exdriving.school.repos;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с инструкторами
 */
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    // получаем запись по логину
    <Optional> Instructor findInstructorById(Integer id);
}
