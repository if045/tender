package com.softserveinc.tender.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DealDto {

    private Integer id;
    private String title;
    private Date date;
    private String businessPartner;
    private BigDecimal sum;
    private String status;
    private boolean newDeal;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBusinessPartner() {
        return businessPartner;
    }

    public void setBusinessPartner(String businessPartner) {
        this.businessPartner = businessPartner;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isNewDeal() {
        return newDeal;
    }

    public void setNewDeal(boolean newDeal) {
        this.newDeal = newDeal;
    }
}
