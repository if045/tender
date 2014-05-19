package com.softserveinc.tender.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,length = 11)
    private Integer id;

    @Column(name = "name",unique = true ,nullable = false,length = 10)
    private String name;

    @ManyToMany(mappedBy ="roles")
    private List<User> users;


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