package com.softserveinc.tender.web;


import com.softserveinc.tender.dto.LocationDto;
import com.softserveinc.tender.dto.ItemDto;
import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.facade.TenderServiceFacade;
import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;


@Controller
@RequestMapping("/tenders")
public class TenderController {


    @Autowired
    private TenderService tenderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TenderServiceFacade tenderFacade;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllTenders(Model model) {
        model.addAttribute("tenders", tenderService.findAll());
        model.addAttribute("categories", categoryService.findAllWithCategory());
        return "tenders";
    }

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public
    @ResponseBody
    List<TenderStatusDto> findAllStatuses() {
        return tenderFacade.findTenderStatuses();
    }


    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public
    @ResponseBody
    List<LocationDto> findLocation() {
        return tenderFacade.findLocation();
    }


    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ItemDto> findAllItems() {
        return tenderFacade.findItems();
    }

}
