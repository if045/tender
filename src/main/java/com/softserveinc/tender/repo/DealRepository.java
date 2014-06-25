package com.softserveinc.tender.repo;

import com.softserveinc.tender.entity.Deal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DealRepository extends JpaRepository<Deal, Integer> {

    @Query("select distinct d from Deal d where d.customer.id = :customerId and (1 = :searchFlag or d.bid.proposal.tender.title like :tenderTitle)")
    List<Deal> findAllDealsForCustomer(@Param("customerId") Integer id,
                                       Pageable pageable,@Param("tenderTitle") String searchParam,
                                       @Param("searchFlag") Integer searchFlag);

    @Query("select distinct d from Deal d where d.seller.id = :sellerId and (1 = :searchFlag or d.bid.proposal.tender.title like :tenderTitle)")
    List<Deal> findAllDealsForSeller(@Param("sellerId") Integer id,
                                     Pageable pageable,@Param("tenderTitle") String searchParam,
                                     @Param("searchFlag") Integer searchFlag);

    @Query("select count(distinct d) from Deal d where d.customer.id = :customerId")
    Long getDealsNumberForCustomer(@Param("customerId") Integer id);

    @Query("select count(distinct d) from Deal d where d.seller.id = :sellerId")
    Long getDealsNumberForSeller(@Param("sellerId") Integer id);

    @Query("select count(distinct d) from Deal d, User u where d.seller.id = :sellerId and d.status.id IN (1,2) and u.id = :sellerId and d.date > u.myDealsDate")
    Long getNewDealsNumberForSeller(@Param("sellerId") Integer id);

    List<Deal> findByProposalId(Integer proposalId);

    @Query("select d from Deal d inner join d.bid b where b.unit.id = :unitId")
    List<Deal> findByUnitId(@Param("unitId")Integer unitId);

}
