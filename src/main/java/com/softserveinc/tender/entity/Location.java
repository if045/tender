package com.softserveinc.tender.entity;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    private static final int LOCATION_NAME_LENGTH = 30;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, unique = true, length = LOCATION_NAME_LENGTH)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}