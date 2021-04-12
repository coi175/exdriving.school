package com.exdriving.school.service;

import com.exdriving.school.domain.Instructor;

public interface InstructorService {
    // поиск инструктора по ID
    public Instructor findInstructorByID(Integer id);
}
