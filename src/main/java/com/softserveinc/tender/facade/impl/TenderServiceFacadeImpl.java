package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationSaveDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.TenderSaveDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.dto.UnitSaveDto;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.facade.TenderServiceFacade;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.service.ItemService;
import com.softserveinc.tender.service.MeasurementService;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.service.TenderStatusService;
import com.softserveinc.tender.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service("tenderServiceFacade")
@Transactional
public class TenderServiceFacadeImpl  implements TenderServiceFacade{

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
    private ProfileService profileService;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private UnitService unitService;

    public List<TenderStatusDto> findTenderStatuses() {
        List<TenderStatusDto> statusesDto = new ArrayList<>();
        for (TenderStatus tenderStatus : tenderStatusService.getAllTenderStatuses()) {
            statusesDto.add(modelMapper.map(tenderStatus, TenderStatusDto.class));
        }
        return statusesDto;
    }

    public List<ItemDto> findTendersItems(TenderFilter tenderFilter) {
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : itemService.findAllItemsByTenders(tenderFilter)) {
            itemDtos.add(modelMapper.map(item, ItemDto.class));
        }
        return itemDtos;
    }

    @Override
    public List<TenderDto> findByCustomParams(TenderFilter tenderFilter) {
        List<Tender> tenders=tenderService.findByCustomParameters(tenderFilter);
        return mapTenders(tenders);
    }

    private List<TenderDto> mapTenders(List<Tender> tenders){
        List<TenderDto> tenderDtos=new ArrayList<>();
        for(Tender tender:tenders){
            tenderDtos.add(mapTender(tender));
        }
        return tenderDtos;
    }

    private TenderDto mapTender(Tender tender){
        TenderDto tenderDto=new TenderDto();
        List<String> locations=new ArrayList<>();
        List<String> categories=new ArrayList<>();
        tenderDto.setId(tender.getId());
        tenderDto.setTitle(tender.getTitle());
        tenderDto.setAuthorName(tender.getAuthor().getFirstName());
        tenderDto.setCreateDate(tender.getCreateDate());
        tenderDto.setEndDate(tender.getEndDate());
        tenderDto.setStatus(tender.getStatus().getName());
        tenderDto.setSuitablePrice(tender.getSuitablePrice());
        for(int i=0 ;i<tender.getLocations().size();i++){
            locations.add(i,tender.getLocations().get(i).getName());
        }
        tenderDto.setLocations(locations);
        for(int i=0 ;i<tender.getUnits().size();i++){
            categories.add(i,tender.getUnits().get(i).getItem().getCategory().getName());
        }
        tenderDto.setCategories(categories);
        tenderDto.setProposals(tender.getProposals().size());
        return tenderDto;
    }

    public List<LocationDto> findLocations() {
        List<LocationDto> locationDto = new ArrayList<>();
        for (Location location : locationService.getTendersLocation()) {
            locationDto.add(modelMapper.map(location, LocationDto.class));
        }
        return locationDto;
    }

    public List<CategoryDto> findCategories() {
        List<Category> categories=categoryService.findAllWithCategory();
        return mapCategories(categories);
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

    @Override
    public void saveTender(TenderSaveDto tenderSaveDto) {
        String pattern = "yyyy/MM/dd";
        Date date=null;
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(pattern);
        try{
            date = formatter.parse(tenderSaveDto.getEndDate());
        }catch( ParseException e ){
            e.printStackTrace();
        }
        List<Location> locations=new ArrayList<>();
        for (LocationSaveDto locationSaveDto:tenderSaveDto.getLocations()) {
            if (locationSaveDto.getId()!=0){
                locations.add(locationService.findById(locationSaveDto.getId()));
            }else{
                locations=locationService.findAll();
            }
        }

        Tender tender=new Tender();
        tender.setLocations(locations);
        tender.setStatus(tenderStatusService.findByName("Open"));
        tender.setTitle(tenderSaveDto.getTitle());
        tender.setDescription(tenderSaveDto.getDescription());
        tender.setSuitablePrice(tenderSaveDto.getSuitablePrice());
        tender.setCreateDate(new Date());
        tender.setEndDate(date);
        tender.setAuthor(profileService.findProfileById(8));
        Tender tender1=tenderService.save(tender);
        List<Unit> units=new ArrayList<>();
        for (UnitSaveDto unitSaveDto:tenderSaveDto.getUnits()) {
            Unit unit=new Unit();
            unit.setQuantity(unitSaveDto.getQuantity());
            unit.setMeasurement(measurementService.findByName(unitSaveDto.getMeasurment()));
            if (itemService.findByName(unitSaveDto.getItem())!=null){
                unit.setItem(itemService.findByName(unitSaveDto.getItem()));
            }else{
                Item item=new Item();
                item.setName(unitSaveDto.getItem());
                item.setCategory(categoryService.findByName(unitSaveDto.getCategory()));
                item.setType(unitSaveDto.getItemType());
                unit.setItem(itemService.save(item));
            }
            unit.setTender(tender1);

            units.add(unitService.save(unit));
        }
        tender.setUnits(units);
        tenderService.save(tender);
    }
}
