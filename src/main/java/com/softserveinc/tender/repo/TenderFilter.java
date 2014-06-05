package com.softserveinc.tender.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TenderFilter {
    private static final Integer OPEN_TENDER_STATUS = 1;
    private static final Integer IN_PROGRESS_TENDER_STATUS = 2;

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

    private String categoryName;
    private Character type;
    private Integer typeFlag;

    public TenderFilter(List<Integer> categories) {
        if (categories==null) {
            setCategoryFlag(1);
        }else {
            this.categories =categories;
            setCategoryFlag(0);
        }
    }

    public TenderFilter(String categoryName, Character type) {
        if (categoryName==null) {
            setCategoryFlag(1);
        }else {
            this.categoryName = categoryName;;
            setCategoryFlag(0);
        }
        if (type==null) {
            setTypeFlag(1);
        }else {
            this.type = type;
            setTypeFlag(0);
        }
    }

    public TenderFilter(Double minPrice, Double maxPrice, List<Integer> categories, List<Integer> locations, List<Integer> items, List<Integer> statuses, Date minDate, Date maxDate) {
        this.minPrice = (minPrice!=null)?minPrice:0;
        this.maxPrice = (maxPrice!=null)?maxPrice:999999999.99;
        if (categories==null) {
            setCategoryFlag(1);
        }else {
            this.categories =categories;
            setCategoryFlag(0);
        }
        if (locations==null){
            setLocationFlag(1);
        }else {
            this.locations = locations;
            setLocationFlag(0);
        }
        if (items==null){
            setItemFlag(1);
        }else {
            this.items = items;
            setItemFlag(0);
        }
        if (statuses==null) {
            List<Integer> list = new ArrayList<>();
            list.add(OPEN_TENDER_STATUS);
            list.add(IN_PROGRESS_TENDER_STATUS);
            setStatuses(list);
        }else {
            this.statuses = statuses;
        }
        this.minDate = (minDate!=null)?minDate:new Date();
        this.maxDate = (maxDate!=null)?maxDate:new Date((new Date().getTime())+2419200000l);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public Integer getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
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
