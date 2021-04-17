package com.exdriving.school.service;
import com.exdriving.school.domain.LessonPlace;

import java.util.Date;
import java.util.List;
/**
 * Сервис, через который контроллер работает с местами занятий в базе данных
 */
public interface LessonPlaceService {
    /**
     * Возвращает список мест занятий (все записи из таблицы lesson_place)
     * @return List<LessonPlace>
     */
    public List<LessonPlace> findAll();

    /**
     * Возвращает место занятие по его адресу
     * @param address
     * @return LessonPlace
     */
    public LessonPlace findLessonPlaceByAddress(String address);

    /**
     * Добавляет новое место занятия в базу данных
     * @param address
     */
    public void addPlace(String address);
}
