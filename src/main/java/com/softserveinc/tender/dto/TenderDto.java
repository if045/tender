package com.softserveinc.tender.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TenderDto {

    private Integer id;
    private String title;
    private String authorName;
    private Date createDate;
    private Date endDate;
    private String status;
    private BigDecimal suitablePrice;
    private List<String> locations;
    private Set<String> categories;
    private Integer proposals;
    private String description;
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Integer getProposals() {
        return proposals;
    }

    public void setProposals(Integer proposals) {
        this.proposals = proposals;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getSuitablePrice() {
        return suitablePrice;
    }

    public void setSuitablePrice(BigDecimal suitablePrice) {
        this.suitablePrice = suitablePrice;
    }
}
