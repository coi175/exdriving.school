package com.exdriving.school.service;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Lesson;
import com.exdriving.school.domain.Mark;

import java.util.List;

public interface MarkService {
    public List<Mark> findAllByLesson(Lesson lesson);
    public void addMark(Lesson lesson, Client client, int mark);
}
