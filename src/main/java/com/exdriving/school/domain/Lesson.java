package com.exdriving.school.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name="date")
    @Getter @Setter
    private Date date;

    @Column(name="students_limit")
    @Getter @Setter
    private int studentsLimit;

    @ToString.Exclude
    @OneToMany(mappedBy="lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Getter @Setter
    private List<Client> clients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="instructor_ID")
    @Getter @Setter
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name="place_ID")
    @Getter @Setter
    private LessonPlace place;

    public Lesson() {}

    public Lesson(Date date, int studentsLimit) {
        this.date = date;
        this.studentsLimit = studentsLimit;
    }

}
