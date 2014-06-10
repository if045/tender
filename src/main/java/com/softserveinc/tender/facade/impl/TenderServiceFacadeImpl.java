package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationSaveDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.TenderSaveDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.dto.UnitSaveDto;
import com.softserveinc.tender.dto.ProposalDto;
import com.softserveinc.tender.dto.UnitDto;
import com.softserveinc.tender.dto.TendersNumberDto;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.facade.TenderServiceFacade;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.service.ItemService;
import com.softserveinc.tender.service.MeasurementService;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.service.ProposalService;
import com.softserveinc.tender.service.TenderStatusService;
import com.softserveinc.tender.service.UnitService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private ProfileService profileService;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private ProposalService proposalService;

    @Override
    public List<TenderDto> findByCustomParams(TenderFilter tenderFilter, Pageable pageable) {
        List<Tender> tenders = tenderService.findByCustomParameters(tenderFilter, pageable);
        return mapTenders(tenders);
    }

    @Override
    public TendersNumberDto findByCustomParamsResultSize(TenderFilter tenderFilter) {
        Long tendersNumber = tenderService.findByCustomParametersResultSize(tenderFilter);
        TendersNumberDto tendersNumberDto = new TendersNumberDto();
        tendersNumberDto.setTendersNumber(tendersNumber);

        return tendersNumberDto;
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

        for (Location location : tender.getLocations()) {
            locations.add(location.getName());
        }
        tenderDto.setLocations(locations);

        for (Unit unit : tender.getUnits()) {
            categories.add(unit.getItem().getCategory().getName());
        }
        tenderDto.setCategories(categories);
        tenderDto.setProposals(tender.getProposals().size());

        return tenderDto;
    }

    @Override
    public List<UnitDto> findUnitsByTenderId(Integer tenderId) {
        List<Unit> units = unitService.findUnitsByTenderId(tenderId);
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

    private List<CategoryDto> mapCategories(List<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categories) {
            categoryDtos.add(modelMapper.map(category, CategoryDto.class));
        }
        return categoryDtos;
    }

    @Override
    public void saveTender(TenderSaveDto tenderSaveDto) {
        String pattern = "yyyy/MM/dd";
        Date date = null;
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(pattern);
        try {
            date = formatter.parse(tenderSaveDto.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Location> locations = new ArrayList<>();
        for (LocationSaveDto locationSaveDto : tenderSaveDto.getLocations()) {
            if (locationSaveDto.getId() != 0) {
                locations.add(locationService.findById(locationSaveDto.getId()));
            } else {
                locations = locationService.findAll();
            }
        }
        Tender tender = new Tender();
        tender.setLocations(locations);
        tender.setStatus(tenderStatusService.findByName("Open"));
        tender.setTitle(tenderSaveDto.getTitle());
        tender.setDescription(tenderSaveDto.getDescription());
        tender.setSuitablePrice(tenderSaveDto.getSuitablePrice());
        tender.setCreateDate(new Date());
        tender.setEndDate(date);
        tender.setAuthor(profileService.findProfileById(8));
        Tender tender1 = tenderService.save(tender);
        List<Unit> units = new ArrayList<>();
        for (UnitSaveDto unitSaveDto : tenderSaveDto.getUnits()) {
            Unit unit = new Unit();
            unit.setQuantity(unitSaveDto.getQuantity());
            unit.setMeasurement(measurementService.findByName(unitSaveDto.getMeasurment()));
            if (itemService.findByName(unitSaveDto.getItem()) != null) {
                unit.setItem(itemService.findByName(unitSaveDto.getItem()));
            } else {
                Item item = new Item();
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

    public void updateTenderWithStatus(Integer tenderId, String statusName) {
        tenderService.updateTenderWithStatus(tenderId, statusName);
    }

    public List<ProposalDto> findTendersProposals(Integer tenderId) {
        return mapTendersProposals(tenderId);
    }

    private List<ProposalDto> mapTendersProposals(Integer tenderId) {
        List<ProposalDto> proposalDtos = new ArrayList<>();
        for (Proposal proposal : proposalService.findByTenderId(tenderId)) {
            proposalDtos.add(mapTenderProposal(proposal));
        }
        return proposalDtos;
    }

    private ProposalDto mapTenderProposal(Proposal proposal) {
        ProposalDto proposalDto = new ProposalDto();

        proposalDto.setId(proposal.getId());
        proposalDto.setFullName(proposalDto.convertIntoFullName(proposal));
        proposalDto.setNumberOfBids(proposal.getBids().size());
        proposalDto.setTotalBidsPrice(proposalDto.countTotalBidsPrice(proposal));

        return proposalDto;
    }
}
