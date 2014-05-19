package com.softserveinc.tender.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category")
public class Category {


    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,length = 11)
    private Integer id;

    @Column(name = "name",nullable = false,length = 30)
    private String name;

    @Column(name = "parent",nullable = false,length = 11)
    private Category parent;


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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
