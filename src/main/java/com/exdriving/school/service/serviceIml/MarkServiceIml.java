package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Lesson;
import com.exdriving.school.domain.Mark;
import com.exdriving.school.repos.MarkRepository;
import com.exdriving.school.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceIml implements MarkService {
    @Autowired
    MarkRepository markRepository;
    @Override

    public List<Mark> findAllByLesson(Lesson lesson) {
        return markRepository.findAllByLesson(lesson);
    }

    @Override
    public void addMark(Lesson lesson, Client client, int mk) {
        for(Mark m : client.getMarks()) {
            if(m.getLesson().equals(lesson)) {
                m.setMark(mk);
                markRepository.saveAndFlush(m);
                return;
            }
        }
        Mark mark = new Mark();
        mark.setMark(mk);
        mark.setClient(client);
        mark.setLesson(lesson);
        markRepository.saveAndFlush(mark);
    }
}
