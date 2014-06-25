package com.softserveinc.tender.facade.impl;

import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.facade.ProposalServiceFacade;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.ProposalService;
import com.softserveinc.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("proposalServiceFacade")
@Transactional
public class ProposalServiceFacadeImpl implements ProposalServiceFacade{
    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private TenderService tenderService;

    @Override
    public Long getNewProposalsNumber() {
        return proposalService.getNewProposalsNumberForCustomerId(profileService.findProfileByUserLogin
                (SecurityContextHolder.getContext().getAuthentication().getName()).getId());
    }

    @Override
    public void setAuthorSawByTenderId(Integer tenderId) {
        Tender tender = tenderService.findOne(tenderId);
        if (tender.getAuthor().equals(profileService.findProfileByUserLogin
                (SecurityContextHolder.getContext().getAuthentication().getName()))) {

            List<Proposal> tenderProposals = proposalService.findByTenderId(tenderId);
            for (Proposal proposal: tenderProposals) {
                proposal.setTenderAuthorSaw(true);
                proposalService.save(proposal);
            }
        }
    }
}
