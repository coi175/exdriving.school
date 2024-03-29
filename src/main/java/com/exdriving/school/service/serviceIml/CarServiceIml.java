package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.Car;
import com.exdriving.school.repos.CarRepository;
import com.exdriving.school.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceIml implements CarService {
    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Integer id) {
        return carRepository.findCarById(id);
    }

    @Override
    public void addCar(String brand, String model, String number) {
        // создаем новую машину
        Car car = new Car();
        // устанавиваем инфу о ней
        car.setModel(brand + " " + model);
        car.setStateNumber(number);
        // сохраняем в базе данных
        carRepository.saveAndFlush(car);
    }
}
