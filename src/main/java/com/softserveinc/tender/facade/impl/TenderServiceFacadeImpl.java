package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.*;
import com.softserveinc.tender.entity.*;
import com.softserveinc.tender.facade.TenderServiceFacade;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.service.*;
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

    @Autowired
    private ProposalService proposalService;

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

    public List<ProposalDto> findTenderProposals(Integer tenderId) {
        return mapTenderProposals(tenderId);
    }

    private List<ProposalDto> mapTenderProposals(Integer tenderId) {
        List<ProposalDto> proposalDtos = new ArrayList<>();

        for(Proposal proposal : proposalService.findByTender(tenderId)) {
            ProposalDto proposalDto = new ProposalDto();

            proposalDto.setId(proposal.getId());
            proposalDto.setFullName(proposal);
            proposalDto.setNumberOfBids(proposal.getBids().size());
            proposalDto.setTotalPrice(proposal);

            proposalDtos.add(proposalDto);
        }
        return proposalDtos;
    }
}
