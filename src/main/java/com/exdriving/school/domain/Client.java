package com.exdriving.school.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="email")
    private String email;
    @Column(name="instructor_ID")
    private Integer instructorID;
    @Column(name="lesson_ID")
    private Integer lessonID;
    @Column(name="remaining_hours")
    private int remainingHours;
    @Column(name="spent_hours")
    private int spentHours;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;


}
