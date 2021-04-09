package com.exdriving.school.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "auth_data")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name= "username")
    private String username;
    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

    @Column(name="client_ID")
    private Integer clientID;

    @Column(name="instructor_ID")
    private Integer instructorID;

    public User() {}

    public User(String username, String password, String role, Integer clientID, Integer instructorID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.clientID = clientID;
        this.instructorID = instructorID;
    }


}
