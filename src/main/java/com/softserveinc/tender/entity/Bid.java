package com.softserveinc.tender.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "bid")
public class Bid {

    private static final int MINIMUM_SUM_VALUE = 0;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "price", nullable = false)
    @Size(min = MINIMUM_SUM_VALUE)
    private Double price;

    @Column(name = "date", nullable = false)
    private Date date;

    @OneToMany
    @JoinColumn(name = "unit_id")
    private Unit unit;


    @OneToMany
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}
