package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.User;

import java.util.List;

public interface ProposalService {

    List<Proposal> findByTenderId(Integer id);

    List<Proposal> findBySeller(User seller);

    Proposal findById(Integer id);

    Proposal save(Proposal proposal);

    void deleteById(Integer id);

    Long getNewProposalsNumberForCustomerId(Integer id);
    Long getTenderNewProposalsForCustomer(Integer tenderId);
}
