package com.exdriving.school.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Car car;

    @BeforeEach
    void init() {
         car = new Car();
    }

    @Test
    void getId() {
        car.setId(1);
        assertEquals(1, car.getId());
    }

    @Test
    void getStateNumber() {
        car.setStateNumber("A145RU");
        assertEquals("A145RU", car.getStateNumber());
    }

    @Test
    void getModel() {
        car.setModel("Reno Logan");
        assertEquals("Reno Logan", car.getModel());
    }

    @Test
    void getInstructor() {
        Instructor instructor = new Instructor();
        instructor.setId(1);
        car.setInstructor(instructor);
        assertEquals(instructor, car.getInstructor());
    }

    @Test
    void setId() {
        car.setId(1);
        assertEquals(1, car.getId());
    }

    @Test
    void setStateNumber() {
        car.setStateNumber("A145RU");
        assertEquals("A145RU", car.getStateNumber());
    }

    @Test
    void setModel() {
        car.setModel("Reno Logan");
        assertEquals("Reno Logan", car.getModel());
    }

    @Test
    void setInstructor() {
        Instructor instructor = new Instructor();
        instructor.setId(1);
        car.setInstructor(instructor);
        assertEquals(instructor, car.getInstructor());
    }
}