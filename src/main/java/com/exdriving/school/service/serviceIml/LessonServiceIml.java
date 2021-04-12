package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.Instructor;
import com.exdriving.school.domain.Lesson;
import com.exdriving.school.domain.LessonPlace;
import com.exdriving.school.repos.LessonRepository;
import com.exdriving.school.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LessonServiceIml implements LessonService {
    @Autowired
    LessonRepository lessonRepository;

    @Override
    public void deleteLessonByID(Integer id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public Lesson findLessonByID(Integer id) {
        return lessonRepository.findLessonById(id);
    }

    @Override
    public void createLesson(Date date, Instructor instructor, LessonPlace lessonPlace, Integer studentsLimit) {
        Lesson lesson = new Lesson();
        lesson.setDate(date);
        lesson.setInstructor(instructor);
        lesson.setPlace(lessonPlace);
        lesson.setStudentsLimit(studentsLimit);

        lessonRepository.saveAndFlush(lesson);
    }
}
