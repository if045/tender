package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.facade.ProposalServiceFacade;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("proposalServiceFacade")
@Transactional
public class ProposalServiceFacadeImpl implements ProposalServiceFacade{
    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ProfileService profileService;

    @Override
    public Long getNewProposalsNumber() {
        return proposalService.getNewProposalsNumberForCustomerId(profileService.findProfileByUserLogin
                (SecurityContextHolder.getContext().getAuthentication().getName()).getId());
    }
}
