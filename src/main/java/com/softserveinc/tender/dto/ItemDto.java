package com.softserveinc.tender.dto;

public class ItemDto {

    private Integer id;
    private String name;
    private Character type;

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

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
