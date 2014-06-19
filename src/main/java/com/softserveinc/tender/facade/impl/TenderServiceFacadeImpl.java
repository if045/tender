package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.dto.BidDto;
import com.softserveinc.tender.dto.BidSaveDto;
import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.ProposalSaveDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.TenderSaveDto;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.dto.UnitSaveDto;
import com.softserveinc.tender.dto.ProposalDto;
import com.softserveinc.tender.dto.UnitDto;
import com.softserveinc.tender.dto.TendersNumberDto;
import com.softserveinc.tender.entity.Bid;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Deal;
import com.softserveinc.tender.entity.Item;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.Role;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.facade.DealServiceFacade;
import com.softserveinc.tender.facade.TenderServiceFacade;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.service.BidService;
import com.softserveinc.tender.service.DealService;
import com.softserveinc.tender.service.DealStatusService;
import com.softserveinc.tender.service.ItemService;
import com.softserveinc.tender.service.MeasurementService;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.service.ProposalService;
import com.softserveinc.tender.service.TenderStatusService;
import com.softserveinc.tender.service.UnitService;
import com.softserveinc.tender.service.UserService;
import com.softserveinc.tender.service.impl.MailService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;
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

    @Autowired
    private UserService userService;

    @Autowired
    private BidService bidService;

    @Autowired
    private MailService mailService;

    @Autowired
    private DealStatusService dealStatusService;

    @Autowired
    private DealService dealService;

    @Autowired
    private DealServiceFacade dealServiceFacade;

    private static final String DATE_FORMAT_FROM_CLIENT="yyyy/MM/dd";
    private static final String DEAL_CREATE_STATUS = "in progress";
    private static final String TENDER_STATUS_IN_PROGRESS = "In progress";
    private static final int PORT = 8080;
    private static final String TENDER_VIEW_URL = "tenderView/";
    private static final String MESSAGE_PROPOSAL_TITLE = "new proposal";

    @Override
    public List<TenderDto> findByCustomParams(TenderFilter tenderFilter, Pageable pageable) {
        if (tenderFilter.getUserTendersFlag() != null) {
            tenderFilter.setProfileId(profileService.findProfileByUserLogin(SecurityContextHolder.getContext()
                    .getAuthentication().getName()).getId());
        }
        List<Tender> tenders = tenderService.findByCustomParameters(tenderFilter, pageable);
        return mapTenders(tenders);
    }

    @Override
    public TendersNumberDto getTendersNumber(TenderFilter tenderFilter) {
        Long tendersNumber = tenderService.getTendersNumber(tenderFilter);
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
        List<String> roles = new ArrayList<>();

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
        if (tender.getProposals()!=null){tenderDto.setProposals(tender.getProposals().size());}
        if (tender.getDescription()!=null){
            tenderDto.setDescription(tender.getDescription());
        }
        if (SecurityContextHolder.getContext().getAuthentication().getName()!="anonymousUser"){
            for (Role role:userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getRoles()){
                roles.add(role.getName());
            }
        }
        tenderDto.setAuthorId(tender.getAuthor().getUser().getId());
        if (SecurityContextHolder.getContext().getAuthentication().getName()!="anonymousUser"){
            tenderDto.setUserId(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        }
        tenderDto.setRoles(roles);
        return tenderDto;
    }

    @Override
    public List<UnitDto> findUnitsByTenderId(Integer tenderId, Pageable pageable) {
        List<Unit> units = unitService.findUnitsByTenderId(tenderId, pageable);
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
        unitDto.setHaveDeals(dealService.findByUnitId(unit.getId()).size() > 0);
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
            categoryDtos.add(mapCategory(category));
        }
        return categoryDtos;
    }

    private CategoryDto mapCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        if(category.getParent() != null) {
            categoryDto.setParentId(category.getParent().getId());
        }

        return categoryDto;
    }

    @Override
    public TenderDto saveTender(TenderSaveDto tenderSaveDto) {
        String pattern = DATE_FORMAT_FROM_CLIENT;
        Date date = null;
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(pattern);
        try {
            date = formatter.parse(tenderSaveDto.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Tender tender = new Tender();
        if (tenderSaveDto.getLocationsIds()==null){
            tender.setLocations(locationService.findAll());
        }else{
            List<Integer> locationsIds=new ArrayList<>();
            for(String s:tenderSaveDto.getLocationsIds().split(",")){
                locationsIds.add(Integer.parseInt(s));
            }
            tender.setLocations(locationService.getLocationsByIds(locationsIds));
        }
        tender.setStatus(tenderStatusService.findByName("Open"));
        tender.setTitle(tenderSaveDto.getTitle());
        if (tenderSaveDto.getDescription()!=null){
            tender.setDescription(tenderSaveDto.getDescription());
        }
        tender.setSuitablePrice(tenderSaveDto.getSuitablePrice());
        tender.setCreateDate(new Date());
        tender.setEndDate(date);
        tender.setAuthor(profileService.findProfileByUserLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        Tender savedTender = tenderService.save(tender);
        List<Unit> units = new ArrayList<>();
        for (UnitSaveDto unitSaveDto : tenderSaveDto.getUnits()) {
            Unit unit = new Unit();
            unit.setQuantity(unitSaveDto.getQuantity());
            unit.setMeasurement(measurementService.findMeasurementById(Integer.parseInt(unitSaveDto.getMeasurment())));
            if (unitSaveDto.getItem().split("\\D+").length==1){
                unit.setItem(itemService.findOne(Integer.parseInt(unitSaveDto.getItem().split("\\D+")[0])));
            }else{
                if (itemService.findOneByCategoryIdAndName(unitSaveDto.getItem(),
                        Integer.parseInt(unitSaveDto.getCategory()))!=null){
                    unit.setItem(itemService.findOneByCategoryIdAndName(unitSaveDto.getItem(),
                            Integer.parseInt(unitSaveDto.getCategory())));
                }else {
                    Item item = new Item();
                    item.setName(unitSaveDto.getItem());
                    item.setCategory(categoryService.findCategoryById(Integer.parseInt(unitSaveDto.getCategory())));
                    item.setType(unitSaveDto.getItemType());
                    unit.setItem(itemService.save(item));
                }
            }
            unit.setTender(savedTender);
            units.add(unitService.save(unit));
        }
        savedTender.setUnits(units);
        return mapTender(savedTender);
    }

    public TenderDto updateTender(Integer tenderId, String statusName, String endDate, String description) {
        String pattern = DATE_FORMAT_FROM_CLIENT;
        Date date = null;
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(pattern);
        if (endDate!=null) {
            try {
                date = formatter.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return mapTender(tenderService.updateTender(tenderId, statusName, date, description));
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
        proposalDto.setDescription(proposal.getDescription());
        proposalDto.setDiscountCurrency(proposal.getDiscountCurrency());
        proposalDto.setDiscountPercentage(proposal.getDiscountPercentage());

        List<BidDto> bidDtos = new ArrayList<>();
        for (Bid bid : proposal.getBids()) {
            BidDto bidDto = new BidDto();
            bidDto.setBidId(bid.getId());
            bidDto.setUnitId(bid.getUnit().getId());
            bidDto.setPrice(bid.getPrice());
            bidDtos.add(bidDto);
        }
        proposalDto.setBidDtos(bidDtos);
        proposalDto.setHaveDeals(dealService.findByProposalId(proposal.getId()).size() > 0);

        return proposalDto;
    }

    @Override
    public ProposalDto saveProposal(ProposalSaveDto proposalSaveDto) {
        Tender tender = tenderService.findOne(proposalSaveDto.getTenderId());
        TenderStatus tenderStatus = tenderStatusService.findByName(TENDER_STATUS_IN_PROGRESS);
        tender.setStatus(tenderStatus);
        Tender updatedTender = tenderService.save(tender);

        Proposal proposal = new Proposal();
        proposal.setSeller(userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        proposal.setTender(updatedTender);
        proposal.setDiscountCurrency(proposalSaveDto.getDiscountCurrency());
        proposal.setDiscountPercentage(proposalSaveDto.getDiscountPercentage());
        proposal.setDescription(proposalSaveDto.getDescription());
        Proposal savedProposal = proposalService.save(proposal);

        List<Bid> bids = new ArrayList<>();
        for (BidSaveDto bidSaveDto: proposalSaveDto.getBids()) {
            Bid bid = new Bid();
            bid.setPrice(bidSaveDto.getPrice());
            bid.setDate(new Date());
            bid.setUnit(unitService.findById(bidSaveDto.getUnitId()));
            bid.setProposal(savedProposal);
            Bid savedBid = bidService.save(bid);
            bids.add(savedBid);
        }
        savedProposal.setBids(bids);

        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            mailService.sendMail(tender.getAuthor().getUser().getLogin(), MESSAGE_PROPOSAL_TITLE,
                                "http://" + hostAddress + ":" + PORT + "/" + TENDER_VIEW_URL + tender.getId());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return mapTenderProposal(savedProposal);
    }

    @Override
    public TenderDto findOneById(Integer id) {
        return mapTender(tenderService.findOne(id));
    }

    @Override
    public List<DealDto> saveProposalDeal(Integer tenderId, Integer proposalId) {
        Proposal proposal = proposalService.findById(proposalId);
        Tender tender = tenderService.findOne(tenderId);

        List<Bid> bids = proposal.getBids();
        List<Deal> deals = new ArrayList<>();
        for (Bid bid: bids) {
            Deal deal = new Deal();
            deal.setBid(bid);
            deal.setCustomer(tender.getAuthor());
            deal.setSeller(proposal.getSeller().getProfile());
            deal.setSum(bid.getPrice());
            deal.setDate(new Date());
            deal.setStatus(dealStatusService.findByName(DEAL_CREATE_STATUS));
            deal.setProposal(proposal);

            Deal savedDeal = dealService.saveDeal(deal);
            deals.add(savedDeal);
        }
        return dealServiceFacade.mapDeals(deals);
    }

    @Override
    public DealDto saveBidDeal(Integer tenderId, Integer proposalId, Integer bidId) {
        Proposal proposal = proposalService.findById(proposalId);
        Tender tender = tenderService.findOne(tenderId);
        Bid bid = bidService.findById(bidId);

        Deal deal = new Deal();
        deal.setBid(bid);
        deal.setCustomer(tender.getAuthor());
        deal.setSeller(proposal.getSeller().getProfile());
        deal.setSum(bid.getPrice());
        deal.setDate(new Date());
        deal.setStatus(dealStatusService.findByName(DEAL_CREATE_STATUS));
        deal.setProposal(proposal);

        Deal savedDeal = dealService.saveDeal(deal);

        return dealServiceFacade.mapDeal(savedDeal);
    }


}
