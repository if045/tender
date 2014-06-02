package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.facade.TenderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tenders")
public class TenderController {

    @Autowired
    private TenderServiceFacade tenderFacade;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<TenderDto> findTenders(
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "items", required = false) List<Integer> items,
            @RequestParam(value = "locations", required = false) List<Integer> locations,
            @RequestParam(value = "categories", required = false) List<Integer> categories,
            @RequestParam(value = "statuses", required = false) List<Integer> statuses,
            @RequestParam(value = "minDate", required = false) Date createDate,
            @RequestParam(value = "maxDate", required = false) Date endDate) {
        return tenderFacade.findByCustomParams(new TenderFilter(minPrice, maxPrice, categories, locations, items, statuses, createDate, endDate));
    }

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public @ResponseBody List<TenderStatusDto> findAllTenderStatuses() {
        return tenderFacade.findTendersStatuses();
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public @ResponseBody List<ItemDto> findTendersItems(
            @RequestParam(value = "categories", required = false) List<Integer> categories) {
        return tenderFacade.findTendersItems(new TenderFilter(null, null, categories, null, null, null, null, null));
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public @ResponseBody List<LocationDto> findLocation() {
        return tenderFacade.findTendersLocations();
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public @ResponseBody List<CategoryDto> findAllCategories() {
        return tenderFacade.findTendersCategories();
    }

}
