package com.softserveinc.tender.facade;

public interface ProposalServiceFacade {
    Long getNewProposalsNumber();
    void setAuthorSawByTenderId(Integer tenderId);
}
