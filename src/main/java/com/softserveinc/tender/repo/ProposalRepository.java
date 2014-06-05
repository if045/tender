package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

    List<Proposal> findByTenderId(Integer id);

    List<Proposal> findBySeller(User user);
}
