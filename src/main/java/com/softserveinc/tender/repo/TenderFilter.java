package com.softserveinc.tender.repo;

import java.util.Date;
import java.util.List;

public class TenderFilter {

    private Double minPrice;
    private Double maxPrice;
    private List<Integer> categories;
    private List<Integer> locations;
    private List<Integer> items;
    private List<Integer> statuses;
    private Date minDate;
    private Date maxDate;

    private Integer categoryFlag;
    private Integer itemFlag;
    private Integer locationFlag;
    private Integer statusFlag;
    private Integer dataFlag;

    public TenderFilter(Double minPrice, Double maxPrice, List<Integer> categories, List<Integer> locations, List<Integer> items, List<Integer> statuses, Date minDate, Date maxDate) {
        this.minPrice = (minPrice != null) ? minPrice : 0;
        this.maxPrice = (maxPrice != null) ? maxPrice : 999999999.99;
        if (categories == null) {
            setCategoryFlag(1);
        } else {
            this.categories = categories;
            setCategoryFlag(0);
        }
        if (locations == null) {
            setLocationFlag(1);
        } else {
            this.locations = locations;
            setLocationFlag(0);
        }
        if (items == null) {
            setItemFlag(1);
        } else {
            this.items = items;
            setItemFlag(0);
        }
        if (statuses == null) {
            setStatusFlag(1);
        } else {
            setStatusFlag(0);
            this.statuses = statuses;
        }
        this.minDate = (minDate != null) ? minDate : new Date((new Date().getTime()) - 604800000l);
        this.maxDate = (maxDate != null) ? maxDate : new Date();
    }

    public Integer getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(Integer dataFlag) {
        this.dataFlag = dataFlag;
    }

    public Integer getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(Integer categoryFlag) {
        this.categoryFlag = categoryFlag;
    }

    public Integer getItemFlag() {
        return itemFlag;
    }

    public void setItemFlag(Integer itemFlag) {
        this.itemFlag = itemFlag;
    }

    public Integer getLocationFlag() {
        return locationFlag;
    }

    public void setLocationFlag(Integer locationFlag) {
        this.locationFlag = locationFlag;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getLocations() {
        return locations;
    }

    public void setLocations(List<Integer> locations) {
        this.locations = locations;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public List<Integer> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Integer> statuses) {
        this.statuses = statuses;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
}
