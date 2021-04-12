package com.exdriving.school.repos;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    <Optional> Lesson findLessonById(Integer integer);
}
