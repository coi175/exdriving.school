package com.exdriving.school.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name="head")
    @Getter @Setter
    private String name;

    @Column(name="text")
    @Getter @Setter
    private String text;

    @ToString.Exclude
    @ManyToMany(mappedBy = "notifications", fetch = FetchType.EAGER)
    @Getter @Setter
    private Set<Client> clients = new HashSet<>();

    public Notification() {}

    public Notification(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Client)) return false;
        return id != null && id.equals(((Notification) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }
}
