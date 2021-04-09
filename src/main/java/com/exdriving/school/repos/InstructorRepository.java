package com.exdriving.school.repos;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    // получаем запись по логину
    <Optional> Instructor findClientById(Integer id);
}
