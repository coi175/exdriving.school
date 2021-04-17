package com.exdriving.school.repos;

import com.exdriving.school.domain.Lesson;
import com.exdriving.school.domain.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для работы с оценками
 */
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    List<Mark> findAllByLesson(Lesson lesson);
}
