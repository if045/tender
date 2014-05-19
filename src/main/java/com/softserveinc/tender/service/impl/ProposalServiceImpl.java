package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.repo.ProposalRepository;
import com.softserveinc.tender.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProposalServiceImpl implements ProposalService {
    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public List<Proposal> findByTender() {
        return proposalRepository.findByTender();
    }

    @Override
    public List<Proposal> findBySeller() {
        return proposalRepository.findBySeller();
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
