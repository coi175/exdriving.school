package com.exdriving.school.repos;

import com.exdriving.school.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы с машинами
 */
public interface CarRepository extends JpaRepository<Car, Integer> {
    <Optional> Car findCarById(Integer id);
}
