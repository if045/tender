package com.softserveinc.tender.facade;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.MeasurementDto;

import java.util.List;

public interface CreateTenderFacade {

    List<ItemDto> findItems();
    List<LocationDto> findLocations();
    List<CategoryDto> findCategories();
    List<MeasurementDto> findMeasurements();
    List<ItemDto> findItems(String categoryName);
    List<ItemDto> findItems(String categoryName,Character type);
}
