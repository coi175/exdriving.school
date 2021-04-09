package com.exdriving.school.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name="state_number")
    @Getter @Setter
    private String stateNumber;

    @Column(name="model")
    @Getter @Setter
    private String model;

    public Car() {}

    public Car(String stateNumber, String model) {
        this.stateNumber = stateNumber;
        this.model = model;
    }
}
