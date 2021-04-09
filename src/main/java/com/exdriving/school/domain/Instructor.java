package com.exdriving.school.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.dynamic.scaffold.MethodGraph;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name="first_name")
    @Getter @Setter
    private String firstName;

    @Column(name="last_name")
    @Getter @Setter
    private String lastName;

    @JoinColumn(name="car_ID")
    @OneToOne()
    @Getter @Setter
    private Car car;

    @OneToMany(mappedBy="instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Getter @Setter
    private List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy="instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Getter @Setter
    private Set<Lesson> lessons = new LinkedHashSet<>();

    public Instructor() {}

    public Instructor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
