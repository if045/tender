package com.softserveinc.tender.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProposalSaveDto {
    private Integer tenderId;
    private String description;
    private Double discountPercentage;
    private BigDecimal discountCurrency;
    private List<BidSaveDto> bids;

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getDiscountCurrency() {
        return discountCurrency;
    }

    public void setDiscountCurrency(BigDecimal discountCurrency) {
        this.discountCurrency = discountCurrency;
    }

    public List<BidSaveDto> getBids() {
        return bids;
    }

    public void setBids(List<BidSaveDto> bids) {
        this.bids = bids;
    }
}
