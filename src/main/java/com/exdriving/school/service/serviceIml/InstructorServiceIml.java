package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.Instructor;
import com.exdriving.school.repos.InstructorRepository;
import com.exdriving.school.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceIml implements InstructorService {
    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public Instructor findInstructorByID(Integer id) {
        return instructorRepository.findInstructorById(id);
    }
}
