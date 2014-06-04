package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.User;
import com.softserveinc.tender.repo.ProposalRepository;
import com.softserveinc.tender.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public List<Proposal> findByTenderId(Integer id) {
        return proposalRepository.findByTenderId(id);
    }

    @Override
    public List<Proposal> findBySeller(User seller) {
        return proposalRepository.findBySeller(seller);
    }

    @Override
    public Proposal findById(Integer id) {
        return proposalRepository.findOne(id);
    }

    @Override
    public void save(Proposal proposal) {
        proposalRepository.save(proposal);
    }

    @Override
    public void deleteById(Integer id) {
        proposalRepository.delete(id);
    }
}
