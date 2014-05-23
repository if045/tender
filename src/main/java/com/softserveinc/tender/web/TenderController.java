package com.softserveinc.tender.web;

import com.softserveinc.tender.service.CategoryService;
import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tenders")
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllTenders(Model model) {
        model.addAttribute("tenders", tenderService.findAll());
        model.addAttribute("categories", categoryService.findAllWithCategory());
        return "tenders";
    }
}


