package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Proposal;

import java.util.List;

public interface ProposalService {
    List<Proposal> findByTender();
    List<Proposal> findBySeller();
    Proposal findById(Integer id);
    void save(Proposal proposal);
    void deleteById(Integer id);
}
