package com.softserveinc.tender.dto;

import java.util.List;

public class TradeSphereDto {

    private List<Integer> categories;
    private List<Integer> locations;
    private List<LocationDto> locationsDto;
    private List<CategoryDto> categoriesDto;

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

    public List<LocationDto> getLocationsDto() {
        return locationsDto;
    }

    public void setLocationsDto(List<LocationDto> locationsDto) {
        this.locationsDto = locationsDto;
    }

    public List<CategoryDto> getCategoriesDto() {
        return categoriesDto;
    }

    public void setCategoriesDto(List<CategoryDto> categoriesDto) {
        this.categoriesDto = categoriesDto;
    }
}
