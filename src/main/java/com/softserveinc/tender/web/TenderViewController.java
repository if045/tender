package com.softserveinc.tender.web;

import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/tenderView")
public class TenderViewController {

    @Autowired
    private UnitService unitService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllTenders(Model model) {
        model.addAttribute("tenderView", unitService.findByTenderID(1));
                return "tenderView";
            }

}
