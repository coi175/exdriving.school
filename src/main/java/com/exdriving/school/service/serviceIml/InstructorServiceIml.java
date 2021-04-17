package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.Car;
import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;
import com.exdriving.school.repos.InstructorRepository;
import com.exdriving.school.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceIml implements InstructorService {
    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public Instructor findInstructorByID(Integer id) {
        return instructorRepository.findInstructorById(id);
    }

    @Override
    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor createInstructor(String firstName, String lastName, Car car) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(firstName);
        instructor.setLastName(lastName);
        instructor.setCar(car);
        return instructorRepository.saveAndFlush(instructor);
    }

    @Override
    public void setNewCar(Instructor instructor, Car car) {
        instructor.setCar(car);
        instructorRepository.saveAndFlush(instructor);
    }
}
