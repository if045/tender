package com.softserveinc.tender.web;

import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/tenders")
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @RequestMapping(method = RequestMethod.GET)
    public String showAllTenders(Model model) {
        model.addAttribute("tenders", tenderService.findAll());
        return "tenders";
    }

    @RequestMapping(value = "/customTenders", method = RequestMethod.GET)
    public String findByCustomParameters(@RequestParam("minPrice") double minPrice,
                                         @RequestParam("maxPrice") double maxPrice,
                                         Model model) {
        model.addAttribute("tenders", tenderService.findByCustomParameters(minPrice, maxPrice));
        return "tenders";
    }
}