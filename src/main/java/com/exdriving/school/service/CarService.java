package com.exdriving.school.service;

import com.exdriving.school.domain.Car;

import java.util.List;

/**
 * Сервис, через который контроллер работает с машинами в базе данных
 */
public interface CarService {
    /**
     * Возвращает все машины из базы данных
     * @return List<Car>
     */
    public List<Car> getAllCars();

    /**
     * Получает машину по ID из базы данных
     * @param id
     * @return Car
     */
    public Car getCarById(Integer id);

    /**
     * Добавляет новую машину в базу данных
     * @param brand
     * @param model
     * @param number
     */
    public void addCar(String brand, String model, String number);
}
