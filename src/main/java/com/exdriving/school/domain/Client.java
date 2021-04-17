package com.exdriving.school.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name="email")
    @Getter @Setter
    private String email;

    @ManyToOne
    @JoinColumn(name="instructor_ID")
    @Getter @Setter
    Instructor instructor;

    @ToString.Exclude
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Getter @Setter
    private List<Mark> marks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="lesson_ID")
    @Getter @Setter
    private Lesson lesson;

    @ToString.Exclude
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Getter @Setter
    private Set<Certificate> certificates = new LinkedHashSet<>();

    @ManyToMany (fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "notification_list",
            joinColumns = @JoinColumn(name = "client_ID"),
            inverseJoinColumns = @JoinColumn(name = "notification_ID")
    )
    @Getter @Setter
    private Set<Notification> notifications = new LinkedHashSet<>();

    @Column(name="remaining_hours")
    @Getter @Setter
    private int remainingHours;

    @Column(name="spent_hours")
    @Getter @Setter
    private int spentHours;

    @Column(name="first_name")
    @Getter @Setter
    private String firstName;

    @Column(name="last_name")
    @Getter @Setter
    private String lastName;

    @Column(name="is_active")
    @Getter @Setter
    private boolean isActive;

    public void addNotification(Notification notification){
        this.notifications.add(notification);
        notification.getClients().add(this);
    }

    public void removeNotification(Notification notification){
        this.notifications.remove(notification);
        notification.getClients().remove(this);
    }
}
