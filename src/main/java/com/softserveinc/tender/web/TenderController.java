package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.DealDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.ProposalDto;
import com.softserveinc.tender.dto.ProposalSaveDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.TenderSaveDto;
import com.softserveinc.tender.dto.UnitDto;
import com.softserveinc.tender.dto.TendersNumberDto;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.facade.TenderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/tenders")
public class TenderController {

    @Autowired
    private TenderServiceFacade tenderFacade;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<TenderDto> findTenders(
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "items", required = false) List<Integer> items,
            @RequestParam(value = "locations", required = false) List<Integer> locations,
            @RequestParam(value = "categories", required = false) Set<Integer> categories,
            @RequestParam(value = "statuses", required = false) List<Integer> statuses,
            @RequestParam(value = "minDate", required = false) Date createDate,
            @RequestParam(value = "maxDate", required = false) Date endDate,
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @RequestParam(value = "orderBy", required = false, defaultValue = "createDate") String orderBy,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") String sortDirection,
            @RequestParam(value = "searchParam", required = false) String searchParam,
            @RequestParam(value = "userRole", required = false) String userRole) {

        Sort.Direction pageSortDirection = Sort.Direction.fromString(sortDirection);

        return tenderFacade.findByCustomParams(new TenderFilter(minPrice, maxPrice, categories,
                        locations, items, statuses, createDate, endDate, searchParam, userRole),
                new PageRequest(pageNumber, pageSize, pageSortDirection, orderBy)
        );
    }

    @RequestMapping(value = "/number", method = RequestMethod.GET)
    public @ResponseBody TendersNumberDto getTendersNumber(
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "items", required = false) List<Integer> items,
            @RequestParam(value = "locations", required = false) List<Integer> locations,
            @RequestParam(value = "categories", required = false) Set<Integer> categories,
            @RequestParam(value = "statuses", required = false) List<Integer> statuses,
            @RequestParam(value = "minDate", required = false) Date createDate,
            @RequestParam(value = "maxDate", required = false) Date endDate,
            @RequestParam(value = "searchParam",required = false) String searchParam,
            @RequestParam(value = "userRole", required = false) String userRole) {

        return tenderFacade.getTendersNumber(new TenderFilter(minPrice, maxPrice, categories, locations,
                items, statuses, createDate, endDate, searchParam, userRole));
    }

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public @ResponseBody List<TenderStatusDto> findAllTenderStatuses() {
        return tenderFacade.findTendersStatuses();
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public @ResponseBody List<ItemDto> findTendersItems(
            @RequestParam(value = "categories", required = false) Set<Integer> categories) {
        return tenderFacade.findTendersItems(new TenderFilter(categories));
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public @ResponseBody List<LocationDto> findLocation() {
        return tenderFacade.findTendersLocations();
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public @ResponseBody List<CategoryDto> findAllCategories() {
        return tenderFacade.findTendersCategories();
    }

    @RequestMapping(value = "/{tenderId}/units", method = RequestMethod.GET)
    public @ResponseBody List<UnitDto> sortUnits(@PathVariable("tenderId") Integer tenderId,
                                                 @RequestParam("direction") String direction,
                                                 @RequestParam("field") String field) {
        Sort sort = new Sort(Sort.Direction.fromString(direction), field);
        Pageable pageRequest = new PageRequest(0, Integer.MAX_VALUE, sort);
        return tenderFacade.findUnitsByTenderId(tenderId, pageRequest);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(value = "/{tenderId}", method = RequestMethod.PUT)
    public @ResponseBody TenderDto updateTender(@PathVariable("tenderId") Integer tenderId,
                                                @RequestParam("statusName") String statusName,
                                                @RequestParam(value = "endDate", required = false) String endDate,
                                                @RequestParam(value = "description", required = false) String description) {
        return tenderFacade.updateTender(tenderId, statusName, endDate, description);
    }

    @RequestMapping(value = "/{id}/proposals", method = RequestMethod.GET)
    public @ResponseBody List<ProposalDto> findTenderProposals(@PathVariable Integer id) {
         return tenderFacade.findTendersProposals(id);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody TenderDto addTender(@RequestBody TenderSaveDto tenderSaveDto) {
        return tenderFacade.saveTender(tenderSaveDto);
    }

    @PreAuthorize("hasRole('SELLER')")
    @RequestMapping(value = "/{id}/proposals", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody ProposalDto addProposal(@RequestBody ProposalSaveDto proposalSaveDto) {
        return tenderFacade.saveProposal(proposalSaveDto);
    }

    @RequestMapping(value = "/{tenderId}/proposals/{proposalId}/deals", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody List<DealDto> createDeal(@PathVariable("tenderId") Integer tenderId,
                                                  @PathVariable("proposalId") Integer proposalId) {
        return tenderFacade.saveProposalDeal(tenderId, proposalId);
    }

    @RequestMapping(value = "/{tenderId}/proposals/{proposalId}/bids/{bidId}/deals", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody DealDto createDeal(@PathVariable("tenderId") Integer tenderId,
                                            @PathVariable("proposalId") Integer proposalId,
                                            @PathVariable("bidId") Integer bidId) {
        return tenderFacade.saveBidDeal(tenderId, proposalId, bidId);
    }

    @RequestMapping(value = "/{tenderId}", method = RequestMethod.GET)
    public @ResponseBody TenderDto getTenderInfo(@PathVariable("tenderId") Integer tenderId){
        return tenderFacade.findOneById(tenderId);
    }
}
