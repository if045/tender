package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.User;

import java.util.List;

public interface ProposalService {

    List<Proposal> findByTender(Tender tender);
    List<Proposal> findBySeller(User seller);
    Proposal findById(Integer id);
    void save(Proposal proposal);
    void deleteById(Integer id);
}
