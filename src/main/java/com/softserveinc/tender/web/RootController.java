package com.softserveinc.tender.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

    @RequestMapping("/tendersHome")
    public String getAllTenders() {
        return "tenders";
    }

    @RequestMapping(value = "/tenderView/{tenderId}", method = RequestMethod.GET)
    public String showTender(@PathVariable("tenderId") Integer tenderId) {
        return "tender";
    }

    @RequestMapping("/mydeals")
    public String getAllDeals() {
        return "deals";
    }

    @RequestMapping(value = "/registration")
    public String registration() {
        return "registration";
    }
}
