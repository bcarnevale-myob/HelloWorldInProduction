package com.company;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    protected Person(){}

    Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
