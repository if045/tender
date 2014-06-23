package com.softserveinc.tender.repo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TenderFilter {

    public static final String PERCENT = "%";
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Set<Integer> categories;
    private List<Integer> locations;
    private List<Integer> items;
    private List<Integer> statuses;
    private Date minDate;
    private Date maxDate;
    private String tenderTitle;

    private Integer categoryFlag;
    private Integer itemFlag;
    private Integer locationFlag;
    private Integer statusFlag;
    private Integer dataFlag;
    private Integer priceFlag;
    private Integer searchFlag;

    private Integer categoryId;
    private Character type;
    private Integer typeFlag;

   // private Integer userTendersFlag;
    private Integer profileId;
    private Integer sellerTendersFlag;
    private Integer customerTendersFlag;
    private String userRole;

    public TenderFilter(Set<Integer> categories) {
        if (categories == null) {
            setCategoryFlag(1);
        } else {
            this.categories = categories;
            setCategoryFlag(0);
        }
    }

    public TenderFilter(Integer categoryId, Character type) {
        if (categoryId == null) {
            setCategoryFlag(1);
        } else {
            this.categoryId = categoryId;
            setCategoryFlag(0);
        }
        if (type == null) {
            setTypeFlag(1);
        } else {
            this.type = type;
            setTypeFlag(0);
        }
    }

    public TenderFilter(BigDecimal minPrice, BigDecimal maxPrice, Set<Integer> categories, List<Integer> locations,
                        List<Integer> items, List<Integer> statuses, Date minDate, Date maxDate, String tenderTitle,
                        String userRole) {

        if (minPrice == null & maxPrice == null) {
            setPriceFlag(1);
        } else {
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
            setPriceFlag(0);
        }
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
        this.minDate = (minDate != null) ? minDate : new Date();
        this.maxDate = (maxDate != null) ? maxDate : new Date((new Date().getTime()) + 2419200000l);
        if (tenderTitle == null) {
            setSearchFlag(1);
        } else {
            setSearchFlag(0);
            this.tenderTitle = PERCENT + tenderTitle.toLowerCase().trim() + PERCENT;
        }
        //this.userTendersFlag = userTendersFlag;
        this.userRole = userRole;
        if (userRole == null) {
            setSellerTendersFlag(1);
            setCustomerTendersFlag(1);
        } else if (userRole.equals("CUSTOMER")) {
            setCustomerTendersFlag(0);
            setSellerTendersFlag(1);
        } else if (userRole.equals("SELLER")) {
            setSellerTendersFlag(0);
            setCustomerTendersFlag(1);
        }
    }

    public String getTenderTitle() {
        return tenderTitle;
    }

    public void setTenderTitle(String tenderTitle) {
        this.tenderTitle = tenderTitle;
    }

    public Integer getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(Integer searchFlag) {
        this.searchFlag = searchFlag;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Integer getPriceFlag() {
        return priceFlag;
    }

    public void setPriceFlag(Integer priceFlag) {
        this.priceFlag = priceFlag;
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

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Set<Integer> getCategories() {
        return categories;
    }

    public void setCategories(Set<Integer> categories) {
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

    /*public Integer getUserTendersFlag() {
        return userTendersFlag;
    }

    public void setUserTendersFlag(Integer userTendersFlag) {
        this.userTendersFlag = userTendersFlag;
    }*/

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getSellerTendersFlag() {
        return sellerTendersFlag;
    }

    public void setSellerTendersFlag(Integer sellerTendersFlag) {
        this.sellerTendersFlag = sellerTendersFlag;
    }

    public Integer getCustomerTendersFlag() {
        return customerTendersFlag;
    }

    public void setCustomerTendersFlag(Integer customerTendersFlag) {
        this.customerTendersFlag = customerTendersFlag;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
