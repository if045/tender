package com.softserveinc.tender.dto;

import java.math.BigDecimal;

public class BidSaveDto {
    private Integer unitId;
    private BigDecimal price;

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
