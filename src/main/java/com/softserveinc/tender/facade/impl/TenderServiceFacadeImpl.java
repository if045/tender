package com.softserveinc.tender.facade.impl;

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
import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.entity.Unit;
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
import com.softserveinc.tender.util.Convertible;
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
import java.util.ArrayList;
import java.util.List;

@Service("tenderServiceFacade")
@Transactional
public class TenderServiceFacadeImpl implements TenderServiceFacade {

    @Autowired
    private TenderStatusService tenderStatusService;

    @Autowired
    private Convertible convertible;

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

    private static final String DATE_FORMAT_FROM_CLIENT="yyyy/MM/dd";
    private static final String DEAL_CREATE_STATUS = "in progress";
    private static final String TENDER_STATUS_IN_PROGRESS = "In progress";
    private static final int PORT = 8080;
    private static final String TENDER_VIEW_URL = "tenderView/";
    private static final String MESSAGE_PROPOSAL_TITLE = "new proposal";
    private static final String TEST_USER_LOGIN = "odin_ogame@ukr.net";

    @Override
    public List<TenderDto> findByCustomParams(TenderFilter tenderFilter, Pageable pageable) {
        List<Tender> tenders = tenderService.findByCustomParameters(tenderFilter, pageable);
        return convertible.mapObjects(tenders, TenderDto.class);
    }

    @Override
    public TendersNumberDto getTendersNumber(TenderFilter tenderFilter) {
        Long tendersNumber = tenderService.getTendersNumber(tenderFilter);
        TendersNumberDto tendersNumberDto = new TendersNumberDto();
        tendersNumberDto.setTendersNumber(tendersNumber);
        return tendersNumberDto;
    }

    public List<TenderStatusDto> findTendersStatuses() {
        return convertible.mapObjects(tenderStatusService.findAllTendersStatuses(), TenderStatusDto.class);
    }

    public List<ItemDto> findTendersItems(TenderFilter tenderFilter) {
        return convertible.mapObjects(itemService.findAllItemsByTenders(tenderFilter), ItemDto.class);
    }

    @Override
    public List<UnitDto> findUnitsByTenderId(Integer tenderId, Pageable pageable) {
        List<Unit> units = unitService.findUnitsByTenderId(tenderId, pageable);
        return convertible.mapObjects(units, UnitDto.class);
    }

    public List<LocationDto> findTendersLocations() {
        return convertible.mapObjects(locationService.getTendersLocations(), LocationDto.class);
    }

    public List<CategoryDto> findTendersCategories() {
        List<Category> categories = categoryService.findAllWithCategory();
        return convertible.mapObjects(categories, CategoryDto.class);
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
        if (SecurityContextHolder.getContext().getAuthentication()!=null){
            tender.setAuthor(profileService.findProfileByUserLogin(SecurityContextHolder.getContext().getAuthentication
                    ().getName()));
        }else {
            tender.setAuthor(profileService.findProfileByUserLogin(TEST_USER_LOGIN));
        }
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
        return convertible.mapObject(savedTender, TenderDto.class);
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
        return convertible.mapObject(tenderService.updateTender(tenderId, statusName, date, description), TenderDto.class);
    }

    public List<ProposalDto> findTendersProposals(Integer tenderId) {
        return convertible.mapObjects(proposalService.findByTenderId(tenderId), ProposalDto.class);
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
        proposal.setTenderAuthorSaw(false);
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
        return convertible.mapObject(savedProposal, ProposalDto.class);
    }

    @Override
    public TenderDto findOneById(Integer id) {
        return convertible.mapObject(tenderService.findOne(id), TenderDto.class);
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
        return convertible.mapObjects(deals, DealDto.class);
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

        return convertible.mapObject(savedDeal, DealDto.class);
    }


}
