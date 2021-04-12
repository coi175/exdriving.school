package com.exdriving.school.repos;

import com.exdriving.school.domain.LessonPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonPlaceRepository extends JpaRepository<LessonPlace, Integer> {
    <Optional> LessonPlace findLessonPlaceByAddress(String address);
}
