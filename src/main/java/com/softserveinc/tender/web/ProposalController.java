package com.softserveinc.tender.web;

import com.softserveinc.tender.facade.ProposalServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/proposals")
public class ProposalController {
    @Autowired
    private ProposalServiceFacade proposalServiceFacade;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public @ResponseBody Long getNewProposalsNumber(){
        return proposalServiceFacade.getNewProposalsNumber();
    }
}
