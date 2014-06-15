package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.CategoryDto;
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
import org.springframework.data.domain.PageRequest;
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
            @RequestParam(value = "pageSize", required = true) Integer pageSize) {

        return tenderFacade.findByCustomParams(new TenderFilter(minPrice, maxPrice, categories, locations, items,
                                                                statuses, createDate, endDate),
                                               new PageRequest(pageNumber, pageSize));
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
            @RequestParam(value = "maxDate", required = false) Date endDate) {

        return tenderFacade.getTendersNumber(new TenderFilter(minPrice, maxPrice, categories, locations,
                items, statuses, createDate, endDate));
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
    public @ResponseBody List<UnitDto> mapUnits(@PathVariable("tenderId") Integer tenderId) {
        return tenderFacade.findUnitsByTenderId(tenderId);
    }

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

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody TenderDto addTender(@RequestBody TenderSaveDto tenderSaveDto) {
        return tenderFacade.saveTender(tenderSaveDto);
    }

    @RequestMapping(value = "/{id}/proposals", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody ProposalDto addProposal(@RequestBody ProposalSaveDto proposalSaveDto) {
        return tenderFacade.saveProposal(proposalSaveDto);
    }

    @RequestMapping(value = "/{tenderId}", method = RequestMethod.GET)
    public @ResponseBody TenderDto getTenderInfo(@PathVariable("tenderId") Integer tenderId){
        return tenderFacade.findOneById(tenderId);
    }
}
