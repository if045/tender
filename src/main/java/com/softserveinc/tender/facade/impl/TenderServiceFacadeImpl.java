package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.facade.TenderServiceFacade;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.service.ItemService;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.service.TenderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
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

    public List<TenderStatusDto> findTenderStatuses() {
        List<TenderStatusDto> statusesDto = new ArrayList<>();
        for (TenderStatus tenderStatus : tenderStatusService.getAllTenderStatuses()) {
            statusesDto.add(modelMapper.map(tenderStatus, TenderStatusDto.class));
        }
        return statusesDto;
    }

    public List<ItemDto> findItems() {
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : itemService.findAllItemsByTenders()) {
            itemDtos.add(modelMapper.map(item, ItemDto.class));
        }
        return itemDtos;
    }

    public List<TenderDto> mapTenders(TenderFilter tenderFilter){
        List<Tender> tenders=tenderService.findByCustomParameters(tenderFilter);
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
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categoryService.findAllWithCategory()) {
            categoryDtos.add(modelMapper.map(category, CategoryDto.class));
        }
        return categoryDtos;
    }
}
