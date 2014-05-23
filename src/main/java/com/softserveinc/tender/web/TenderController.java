package com.softserveinc.tender.web;

import com.softserveinc.tender.dto.TenderStatusDto;
import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.facade.TenderFacade;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.TenderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tenders")
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @Autowired
    private TenderFacade tenderFacade;

    @Autowired
    private TenderStatusService tenderStatusService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAllTenders(Model model) {
        model.addAttribute("tenders", tenderService.findAll());
        return "tenders";
    }

    @RequestMapping(value = "/statuses", method = RequestMethod.GET)
    public @ResponseBody List<TenderStatusDto> findAllStatuses() {

        List<TenderStatusDto> statusesDto = new ArrayList<>();
        List<TenderStatus> statuses = tenderStatusService.findAll();

        for(TenderStatus tenderStatus: statuses) {
            statusesDto.add(tenderFacade.map(tenderStatus));
        }

        return statusesDto;
    }
}
