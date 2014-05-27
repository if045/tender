package com.softserveinc.tender.entity;

import javax.persistence.Id;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "tender")
public class Tender {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "description")
    private String description;

    @Column(name = "suitable_price", nullable = false)
    private Double suitablePrice;

    @OneToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Profile author;

    @ManyToOne
    @JoinColumn(name="status_id", nullable = false)
    private TenderStatus status;

    @OneToMany(mappedBy = "tender")
    private List<Unit> units;

    @ManyToMany
    @JoinTable(name = "tender_location", joinColumns = {@JoinColumn(name = "tender_id")}, inverseJoinColumns = {@JoinColumn(name = "location_id")})
    private List<Location> locations;

    @OneToMany(mappedBy = "tender")
    private List<Proposal> proposals;

    public List<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    public Profile getAuthor() {
        return author;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSuitablePrice() {
        return suitablePrice;
    }

    public void setSuitablePrice(Double suitablePrice) {
        this.suitablePrice = suitablePrice;
    }

    public TenderStatus getStatus() {
        return status;
    }

    public void setStatus(TenderStatus status) {
        this.status = status;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}