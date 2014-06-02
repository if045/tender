package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.MeasurementDto;
import com.softserveinc.tender.facade.CreateTenderFacade;
import com.softserveinc.tender.repo.TenderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class CreateTenderController {

    @Autowired
    private CreateTenderFacade createTenderFacade;

/*    @RequestMapping(value = "/tenders/createtender/items", method = RequestMethod.GET)
    public @ResponseBody List<ItemDto> findItems() {

        return createTenderFacade.findItems();
    }*/

    @RequestMapping(value = "/tenders/createtender/items", method = RequestMethod.GET)
    public @ResponseBody List<ItemDto> findItems(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "type", required = false) Character type) {
        if (category!=null&type!=null){
            return createTenderFacade.findItems(category,type);
        }
        return createTenderFacade.findItems();
    }

    @RequestMapping(value = "/tenders/createtender/locations", method = RequestMethod.GET)
    public @ResponseBody List<LocationDto> findLocations() {
        return createTenderFacade.findLocations();
    }

    @RequestMapping(value = "/tenders/createtender/categories", method = RequestMethod.GET)
    public @ResponseBody List<CategoryDto> findCategories() {
        return createTenderFacade.findCategories();
    }

    @RequestMapping(value = "/tenders/createtender/measurements", method = RequestMethod.GET)
    public @ResponseBody List<MeasurementDto> findMeasurements() {
        return createTenderFacade.findMeasurements();
    }

}
