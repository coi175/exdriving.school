package com.exdriving.school.service;

import com.exdriving.school.domain.Instructor;
import com.exdriving.school.domain.LessonPlace;

import java.util.Date;
import java.util.List;

public interface LessonPlaceService {
    public List<LessonPlace> findAll();

    public LessonPlace findLessonPlaceByAddress(String address);
}
