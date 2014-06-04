package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.dto.UnitDto;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.facade.TenderServiceFacade;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.service.ItemService;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.TenderStatusService;
import com.softserveinc.tender.service.UnitService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("tenderServiceFacade")
@Transactional
public class TenderServiceFacadeImpl implements TenderServiceFacade {

    @Autowired
    private TenderStatusService tenderStatusService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TenderService tenderService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UnitService unitService;

    @Override
     public List<TenderDto> findByCustomParams(TenderFilter tenderFilter) {
        List<Tender> tenders = tenderService.findByCustomParameters(tenderFilter);
        return mapTenders(tenders);
    }

    public List<TenderStatusDto> findTendersStatuses() {
        Type targetListType = new TypeToken<List<TenderStatusDto>>(){}.getType();
        return modelMapper.map(tenderStatusService.findAllTendersStatuses(), targetListType);
    }

    public List<ItemDto> findTendersItems(TenderFilter tenderFilter) {
        Type targetListType = new TypeToken<List<ItemDto>>(){}.getType();
        return modelMapper.map(itemService.findAllItemsByTenders(tenderFilter), targetListType);
    }

    private List<TenderDto> mapTenders(List<Tender> tenders) {
        List<TenderDto> tenderDtos = new ArrayList<>();
        for (Tender tender : tenders) {
            tenderDtos.add(mapTender(tender));
        }
        return tenderDtos;
    }

    private TenderDto mapTender(Tender tender) {
        TenderDto tenderDto = new TenderDto();
        List<String> locations = new ArrayList<>();
        Set<String> categories = new HashSet<>();

        tenderDto.setId(tender.getId());
        tenderDto.setTitle(tender.getTitle());
        tenderDto.setAuthorName(tender.getAuthor().getFirstName());
        tenderDto.setCreateDate(tender.getCreateDate());
        tenderDto.setEndDate(tender.getEndDate());
        tenderDto.setStatus(tender.getStatus().getName());
        tenderDto.setSuitablePrice(tender.getSuitablePrice());

        for (Location location: tender.getLocations()) {
            locations.add(location.getName());
        }
        tenderDto.setLocations(locations);

        for(Unit unit: tender.getUnits()){
            categories.add(unit.getItem().getCategory().getName());
        }
        tenderDto.setCategories(categories);
        tenderDto.setProposals(tender.getProposals().size());

        return tenderDto;
    }
     @Override
     public List<UnitDto> findUnitsByTenderId(Integer tenderId){
         List<Unit> units=unitService.findUnitsByTenderId(tenderId);
         return mapUnits(units);

     }

    private UnitDto mapUnit(Unit unit) {
        UnitDto unitDto=new UnitDto();
        unitDto.setTenderId(unit.getTender().getId());
        unitDto.setId(unit.getId());
        unitDto.setUnitName(unit.getItem().getName());
        unitDto.setItemType(unit.getItem().getType());
        unitDto.setCategoryName(unit.getItem().getCategory().getName());
        unitDto.setQuantity(unit.getQuantity());
        unitDto.setMeasurementName(unit.getMeasurement().getName());
        unitDto.setNumberOfBids(unit.getBids().size());
        return unitDto;
    }

    private List<UnitDto> mapUnits(List<Unit> units){
        List<UnitDto> unitDtos=new ArrayList<>();
        for(Unit unit:units){
            unitDtos.add(mapUnit(unit));
        }
        return unitDtos;
    }

    public List<LocationDto> findTendersLocations() {
        List<LocationDto> locationDto = new ArrayList<>();
        for (Location location : locationService.getTendersLocations()) {
            locationDto.add(modelMapper.map(location, LocationDto.class));
        }
        return locationDto;
    }

    public List<CategoryDto> findTendersCategories() {
        List<Category> categories = categoryService.findAllWithCategory();
        return mapCategories(categories);
    }

    private CategoryDto mapCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        if (category.getParent() != null) {
            categoryDto.setParent(category.getParent().getId());
        }
        return categoryDto;
    }

    private List<CategoryDto> mapCategories(List<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(mapCategory(category));
        }
        return categoryDtos;
    }
}
