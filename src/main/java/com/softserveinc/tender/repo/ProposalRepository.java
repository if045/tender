package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

    @Query("select p from Proposal p where p.tender.id = :tenderId")
    List<Proposal> findByTender(@Param("tenderId") Integer tenderId);

    List<Proposal> findBySeller(Integer sellerId);
}
