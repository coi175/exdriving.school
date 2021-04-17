package com.exdriving.school.service;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;
import com.exdriving.school.domain.Lesson;
import com.exdriving.school.domain.LessonPlace;

import java.util.Date;

/**
 * Сервис, через который контроллер работает с занятиями в базе данных
 */
public interface LessonService {
    /**
     * Удаляет занятие по ID из базы данных
     * @param id
     */
    public void deleteLessonByID(Integer id);

    /**
     * Возвращает занятие из базы данных по ID
     * @param id
     * @return Lesson
     */
    public Lesson findLessonByID(Integer id);

    /**
     * Создает новое занятие
     * @param date
     * @param instructor
     * @param lessonPlace
     * @param studentsLimit
     */
    public void createLesson(Date date, Instructor instructor, LessonPlace lessonPlace, Integer studentsLimit);
}
