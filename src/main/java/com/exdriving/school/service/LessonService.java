package com.exdriving.school.service;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;
import com.exdriving.school.domain.Lesson;
import com.exdriving.school.domain.LessonPlace;

import java.util.Date;

public interface LessonService {
    public void deleteLessonByID(Integer id);
    public Lesson findLessonByID(Integer id);
    public void createLesson(Date date, Instructor instructor, LessonPlace lessonPlace, Integer studentsLimit);
}
