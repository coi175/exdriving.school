package com.exdriving.school.domain;
import com.exdriving.school.domain.Client;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "certificate")
public class Certificate {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="client_ID")
    private Client client;

    @Getter
    @Setter
    @Column(name = "number")
    private int number;

    @Getter
    @Setter
    @Column(name="date")
    private Date date;

    @Getter
    @Setter
    @Column(name = "mark")
    private int mark;

    public Certificate() {}

    public Certificate(int number, Date date, int mark) {
        this.number = number;
        this.date = date;
        this.mark = mark;
    }
}

