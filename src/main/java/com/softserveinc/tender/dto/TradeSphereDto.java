package com.softserveinc.tender.dto;

import java.util.List;

public class TradeSphereDto {

    private List<Integer> categories;
    private List<Integer> locations;

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
}
