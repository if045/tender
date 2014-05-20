package com.softserveinc.tender.web;

import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.TenderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @Autowired
    private TenderStatusService tenderStatusService;

    @RequestMapping(value = "statusFilter/{tender_status}", method = {RequestMethod.POST, RequestMethod.GET})
    public String statusFilter(@PathVariable int tender_status, Model model) {

        model.addAttribute("tenders", tenderService.findTendersByParameters(tender_status));

        return "tenders";
    }

    @RequestMapping(value = "statusFilter", method = RequestMethod.GET)
    public String statusFilter(Model model) {

        model.addAttribute("statuses", tenderStatusService.findAll());

        return "tenders";
    }
}
