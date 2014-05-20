package com.softserveinc.tender.web;

import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filter(@RequestParam("minPrice") double minPrice,
                         @RequestParam("maxPrice") double maxPrice,
                         Model model) {
        model.addAttribute("tendersList", tenderService.filter(minPrice, maxPrice));
        return "tenders";
    }

}
