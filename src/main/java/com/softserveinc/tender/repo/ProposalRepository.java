package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {
    List<Proposal> findByTender(Integer tenderId);
    List<Proposal> findBySeller(Integer sellerId);
}
