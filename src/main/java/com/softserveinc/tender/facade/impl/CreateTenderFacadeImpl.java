package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.MeasurementDto;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Measurement;
import com.softserveinc.tender.facade.CreateTenderFacade;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.service.ItemService;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.service.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("createTenderFacade")
@Transactional
public class CreateTenderFacadeImpl implements CreateTenderFacade{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MeasurementService measurementService;

    @Override
    public List<ItemDto> findItems() {
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : itemService.findAll()) {
            itemDtos.add(modelMapper.map(item, ItemDto.class));
        }
        return itemDtos;
    }

    @Override
    public List<LocationDto> findLocations() {
        List<LocationDto> locationDto = new ArrayList<>();
        for (Location location : locationService.findAll()) {
            locationDto.add(modelMapper.map(location, LocationDto.class));
        }
        return locationDto;
    }

    @Override
    public List<CategoryDto> findCategories() {
        List<Category> categories=categoryService.findAll();
        return mapCategories(categories);
    }

    @Override
    public List<MeasurementDto> findMeasurements() {
        List<MeasurementDto> measurementDtos = new ArrayList<>();
        for (Measurement measurement : measurementService.findAll()) {
            measurementDtos.add(modelMapper.map(measurement, MeasurementDto.class));
        }
        return measurementDtos;
    }

    @Override
    public List<ItemDto> findItems(String categoryName) {
        Category category=categoryService.findByName(categoryName);
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : itemService.findByCategoryId(category.getId())) {
            itemDtos.add(modelMapper.map(item, ItemDto.class));
        }
        return itemDtos;
    }

    @Override
    public List<ItemDto> findItems(String categoryName, Character type) {
        Category category=categoryService.findByName(categoryName);
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : itemService.findByCategoryIdAndType(category.getId(),type)) {
            itemDtos.add(modelMapper.map(item, ItemDto.class));
        }
        return itemDtos;
    }

    private CategoryDto mapCategory (Category category){
        CategoryDto categoryDto=new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        if (category.getParent()!=null) {
            categoryDto.setParent(category.getParent().getId());
        }
        return categoryDto;
    }

    private List<CategoryDto> mapCategories(List<Category> categories){
        List<CategoryDto> categoryDtos=new ArrayList<>();
        for(Category category:categories){
            categoryDtos.add(mapCategory(category));
        }
        return categoryDtos;
    }
}
