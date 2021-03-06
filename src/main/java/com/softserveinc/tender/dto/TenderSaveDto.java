package com.softserveinc.tender.dto;

import java.math.BigDecimal;
import java.util.List;

public class TenderSaveDto {

    private String title;
    private String locationsIds;
    private BigDecimal suitablePrice;
    private String endDate;
    private String description;
    private List<UnitSaveDto> units;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocationsIds() {
        return locationsIds;
    }

    public void setLocationsIds(String locationsIds) {
        this.locationsIds = locationsIds;
    }

    public BigDecimal getSuitablePrice() {
        return suitablePrice;
    }

    public void setSuitablePrice(BigDecimal suitablePrice) {
        this.suitablePrice = suitablePrice;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UnitSaveDto> getUnits() {
        return units;
    }

    public void setUnits(List<UnitSaveDto> units) {
        this.units = units;
    }
}
