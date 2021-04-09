package com.exdriving.school.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lesson_place")
public class LessonPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name = "address")
    @Getter @Setter
    private String address;

    public LessonPlace() {}

    public LessonPlace(String address) {
        this.address = address;
    }
}
