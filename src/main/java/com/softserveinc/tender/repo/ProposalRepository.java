package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

    List<Proposal> findByTenderId(Integer id);

    List<Proposal> findBySeller(User user);

    @Query("SELECT COUNT(p) FROM Proposal p, Tender t " +
            "WHERE t.id = p.tender.id AND t.author.id = :customerId AND p.tenderAuthorSaw = false " +
            "AND t.status.name <> :closeTenderStatus")
    Long findNewProposalsNumberForCustomerId(@Param("customerId")Integer customerId,
                                             @Param("closeTenderStatus")String closeTenderStatus);

    @Query("SELECT COUNT(p) FROM Proposal p " +
            "WHERE p.tender.id = :tenderId AND p.tenderAuthorSaw = false")
    Long findTenderNewProposalsForCustomer(@Param("tenderId") Integer tenderId);

}
