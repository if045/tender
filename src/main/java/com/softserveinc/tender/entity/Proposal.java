package com.softserveinc.tender.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "proposal")
public class Proposal {

    private static final int MINIMUM_SUM_VALUE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "tender_id", nullable = false)
    private Tender tender;

    @Column(name = "discount_percentage")
    @Size(min = MINIMUM_SUM_VALUE)
    private Double discountPercentage;

    @Column(name = "discount_currency")
    @Size(min = MINIMUM_SUM_VALUE)
    private Double discountCurrency;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getDiscountCurrency() {
        return discountCurrency;
    }

    public void setDiscountCurrency(Double discountCurrency) {
        this.discountCurrency = discountCurrency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
