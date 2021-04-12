package com.exdriving.school.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class LessonData {
    @Getter @Setter
    private String date;
    @Getter @Setter
    private String time;
    @Getter @Setter
    private String address;
    @Getter @Setter
    private String studentsLimit;

    public LessonData(String date, String time, String address, String studentsLimit) {
        this.date = date;
        this.time = time;
        this.address = address;
        this.studentsLimit = studentsLimit;
    }

    public LessonData() {
    }
}
