package com.exdriving.school.service;

import com.exdriving.school.domain.Car;
import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;

import java.util.List;

/**
 * сервис для работы с инструкторами в базе данных
 */
public interface InstructorService {
    /**
     * Возвращает инструктора из базы данных по ID
     * @param id
     * @return Instructor
     */
    public Instructor findInstructorByID(Integer id);

    /**
     * Возвращает список инструкторов из базы данных (все записи из таблицы instructor)
     * @return List<Instructor>
     */
    public List<Instructor> getAll();

    /**
     * Создает нового инструктора (добавляет запись в базу данных)
     * @param firstName
     * @param lastName
     * @param car
     * @return
     */
    public Instructor createInstructor(String firstName, String lastName, Car car);

    /**
     * Устанавливает новую машину для инструктора
     * @param instructor
     * @param car
     */
    public void setNewCar(Instructor instructor, Car car);
}
