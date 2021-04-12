package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.LessonPlace;
import com.exdriving.school.repos.LessonPlaceRepository;
import com.exdriving.school.service.LessonPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonPlaceServiceIml implements LessonPlaceService {
    @Autowired
    LessonPlaceRepository lessonPlaceRepository;

    @Override
    public List<LessonPlace> findAll() {
        return lessonPlaceRepository.findAll();
    }

    @Override
    public LessonPlace findLessonPlaceByAddress(String address) {
        return lessonPlaceRepository.findLessonPlaceByAddress(address);
    }
}
