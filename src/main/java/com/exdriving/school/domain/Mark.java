package com.exdriving.school.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name="mark")
    @Getter @Setter
    private int mark;

    @ManyToOne
    @JoinColumn(name="client_ID")
    @Getter @Setter
    Client client;

    @ManyToOne
    @JoinColumn(name="lesson_ID")
    @Getter @Setter
    Lesson lesson;

    public Mark() {}

    public Mark(int mark) {
        this.mark = mark;
    }
}
