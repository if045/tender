package com.softserveinc.tender.entity;

import javax.persistence.Id;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "unit")
public class Unit {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @OneToOne
    @JoinColumn(name = "measurement_id", nullable = false)
    private Measurement measurement;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
