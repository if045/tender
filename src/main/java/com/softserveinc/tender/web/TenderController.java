package com.softserveinc.tender.web;

import com.softserveinc.tender.service.ItemService;
import com.softserveinc.tender.service.LocationService;
import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TenderController {

    @Autowired
    private TenderService tenderService;


    @Autowired
    private LocationService locationService;

    @RequestMapping("/index")
    public String showAllTenders(Model model) {
        model.addAttribute("tenders", tenderService.findAll());
        model.addAttribute("locations", locationService.getTendersLocation());
        return "tenders";
            }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }


}

