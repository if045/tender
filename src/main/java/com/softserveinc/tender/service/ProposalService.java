package com.softserveinc.tender.service;

import com.softserveinc.tender.entity.Proposal;
import java.util.List;

public interface ProposalService {

    List<Proposal> findByTender(Integer tenderId);
    List<Proposal> findBySeller(Integer sellerId);
    Proposal findById(Integer id);
    void save(Proposal proposal);
    void deleteById(Integer id);
}
