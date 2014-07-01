package com.softserveinc.tender.web;

import com.softserveinc.tender.entity.template.Roles;
import com.softserveinc.tender.facade.ProposalServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/proposals")
public class ProposalController {
    @Autowired
    private ProposalServiceFacade proposalServiceFacade;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Long getNewProposalsNumber(@RequestParam("isNew") boolean isNew,
                                                    @RequestParam("userRole") String userRole){
        if (isNew && userRole.equals(Roles.CUSTOMER.toString())) {
            return proposalServiceFacade.getNewProposalsNumber();
        } else return 0L;
    }

    @RequestMapping(value = "/setAuthorSaw", method = RequestMethod.PUT)
    public @ResponseBody void setAuthorSaw(@RequestParam("tenderId") Integer tenderId) {
        proposalServiceFacade.setAuthorSawByTenderId(tenderId);
    }
}
