package com.softserveinc.tender.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "deal")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "bid_id", nullable = false)
    @NotNull
    private Bid bid;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull
    private Profile customer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    @NotNull
    private Profile seller;

    @Column(name = "sum", nullable = false)
    @NotNull
    private Double sum;

    @Column(name = "date", nullable = false)
    @NotNull
    private Date date;

    @OneToOne
    @JoinColumn(name = "status_id", nullable = false)
    @NotNull
    private DealStatus status;

    @OneToOne
    @JoinColumn(name = "proposal_id", nullable = false)
    @NotNull
    private Proposal proposal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public Profile getCustomer() {
        return customer;
    }

    public void setCustomer(Profile customer) {
        this.customer = customer;
    }

    public Profile getSeller() {
        return seller;
    }

    public void setSeller(Profile seller) {
        this.seller = seller;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DealStatus getStatus() {
        return status;
    }

    public void setStatus(DealStatus status) {
        this.status = status;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}
