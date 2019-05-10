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

    @Column(name = "isOwner")
    private Boolean isOwner;

    protected User(){}

    User(String name) {
        this.id = id;
        this.name = name;
        this.isOwner = false;
    }

    User(String name, Boolean isOwner) {
        new User(name);
        this.isOwner = isOwner;
    }

    public String getName() {
        return this.name;
    }

    public String setName() {
        return this.name;
    }
}
