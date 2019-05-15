package com.company;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    protected User(){}

    User(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
