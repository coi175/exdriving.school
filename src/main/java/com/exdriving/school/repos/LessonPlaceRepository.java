package com.exdriving.school.repos;

import com.exdriving.school.domain.LessonPlace;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с местами занятий
 */
public interface LessonPlaceRepository extends JpaRepository<LessonPlace, Integer> {
    <Optional> LessonPlace findLessonPlaceByAddress(String address);
}
