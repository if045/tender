package com.softserveinc.tender.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProposalSaveDto {
    private Integer tenderId;
    private String description;
    private BigDecimal discountPercentage;
    private BigDecimal discountCurrency;
    private List<BidSaveDto> bidSaveDtos;

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

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getDiscountCurrency() {
        return discountCurrency;
    }

    public void setDiscountCurrency(BigDecimal discountCurrency) {
        this.discountCurrency = discountCurrency;
    }

    public List<BidSaveDto> getBidSaveDtos() {
        return bidSaveDtos;
    }

    public void setBidSaveDtos(List<BidSaveDto> bidSaveDtos) {
        this.bidSaveDtos = bidSaveDtos;
    }
}
