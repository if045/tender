package com.softserveinc.tender.entity;

import javax.persistence.*;

@Entity
@Table(name = "conflict_status")
public class ConflictStatus {

    private static final int MEASUREMENT_NAME_LENGTH = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false, length = MEASUREMENT_NAME_LENGTH)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
