package com.exdriving.school.service;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Lesson;
import com.exdriving.school.domain.Mark;

import java.util.List;

/**
 * Сервис, через который контроллер работает с оценками в базе данных
 */
public interface MarkService {
    /**
     * Возвращает все оценки из базы данных
     * @param lesson
     * @return List<Mark>
     */
    public List<Mark> findAllByLesson(Lesson lesson);

    /**
     * Создает новую оценку в базе данных (запись в таблице mark)
     * @param lesson
     * @param client
     * @param mark
     */
    public void addMark(Lesson lesson, Client client, int mark);
}
