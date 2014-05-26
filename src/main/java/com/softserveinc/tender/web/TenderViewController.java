package com.softserveinc.tender.web;

import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tenderViewFor")
public class TenderViewController {

    @Autowired
    private TenderService tenderService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllTenders(Model model) {
        model.addAttribute("tenderViewFor", tenderService.findAll());
                return "tenderViewFor";
    }

}
