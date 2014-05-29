package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.UnitDto;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.facade.TenderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;

@Controller
public class TenderController {

    @Autowired
    private TenderServiceFacade tenderFacade;

    @RequestMapping("")
    public String getAllTenders() {
        return "tenders";
    }

    @RequestMapping(value = "/tenders", method = RequestMethod.GET)
    public @ResponseBody List<TenderDto> findTenders(
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "items", required = false) List<Integer> items,
            @RequestParam(value = "locations", required = false) List<Integer> locations,
            @RequestParam(value = "categories", required = false) List<Integer> categories,
            @RequestParam(value = "statuses", required = false) List<Integer> statuses,
            @RequestParam(value = "minDate", required = false) Date createDate,
            @RequestParam(value = "maxDate", required = false) Date endDate) {
        return tenderFacade.findByCustomParams(new TenderFilter(minPrice,maxPrice,categories,locations,items,statuses,createDate,endDate));
    }

    @RequestMapping(value = "/tenders/statuses", method = RequestMethod.GET)
    public @ResponseBody List<TenderStatusDto> findAllTenderStatuses() {
        return tenderFacade.findTenderStatuses();
    }

    @RequestMapping(value = "/tenders/view{id}", method = RequestMethod.GET)
    public String showTender(@PathVariable("id") Integer id) {
        return "tender";
    }

    @RequestMapping(value = "/tenders/items", method = RequestMethod.GET)
    public @ResponseBody List<ItemDto> findTendersItems(
            @RequestParam(value = "categories", required = false) List<Integer> categories) {
        return tenderFacade.findTendersItems(new TenderFilter(null, null, categories, null, null, null, null, null));
    }

    @RequestMapping(value = "/tenders/locations", method = RequestMethod.GET)
    public @ResponseBody List<LocationDto> findLocations() {
        return tenderFacade.findLocations();
    }

    @RequestMapping(value = "/tenders/categories", method = RequestMethod.GET)
    public @ResponseBody List<CategoryDto> findAllCategories() {
        return tenderFacade.findCategories();
    }

    @RequestMapping(value = "/tenders/unit{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<UnitDto> mapUnits(@PathVariable("id") Integer id) {return tenderFacade.findUnits(id);
    }
}
